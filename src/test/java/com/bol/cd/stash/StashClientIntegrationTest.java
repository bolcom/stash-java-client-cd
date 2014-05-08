package com.bol.cd.stash;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bol.cd.stash.model.Branch;
import com.bol.cd.stash.model.Commit;
import com.bol.cd.stash.model.LinesPage;
import com.bol.cd.stash.model.LinesPage.Line;
import com.bol.cd.stash.model.Page;
import com.bol.cd.stash.model.Project;
import com.bol.cd.stash.model.PullRequest;
import com.bol.cd.stash.model.Ref;
import com.bol.cd.stash.model.Repository;
import com.bol.cd.stash.model.User;
import com.bol.cd.stash.model.UserRole;
import com.bol.cd.stash.request.CreateBranch;
import com.bol.cd.stash.request.DeleteBranch;
import com.bol.cd.stash.request.PullRequestState;


/**
 * You can enable this class to do an integration test on your Stash installation. It will do various actions like creating projects, repositories and branches and pushing files to them. <br/>
 * These tests assume that:
 * <ul>
 * <li> you have Stash running on localhost, port 7990</li>
 * <li> the administrative user has userName 'admin' and password 'password'
 * <li> you have GIT available on your path </li>
 * <li> you don't care about the project with projectKey 'TPT'.
 *
 * </ul>
 *
 * @author ckramer
 *
 */
public class StashClientIntegrationTest {


    private final static String url = "localhost:7990";
    private final static String userName = "admin";
    private final static String password = "password";
    
    private final static Logger log = LoggerFactory.getLogger(StashClientIntegrationTest.class);
    private final static String gitProtocol = "http://";
    
    private final static String branchName = "feature/ABC-123";
    private final static String projectKey = "TPT";
    private static final String featureBranchId = "refs/heads/feature/ABC-123";

    private StashApi stashApi;

    @Before
    public void before() {
        log.info("Creating stash client...");
        stashApi = StashClient.create("http://" + url, userName, password);
        log.info("Preparing stash, cleaning up leftovers..");
        final Page<Project> projectsPage = stashApi.getProjects();

        for (final Project project : projectsPage.getValues()) {
            if (project.getKey().equals(projectKey)) {
                log.info("Cleaning project '{}'...", project.getName());
                final Page<Repository> repositoriesPage = stashApi.getRepositories(projectKey);
                for (final Repository repository : repositoriesPage.getValues()) {
                    log.info("Cleaning repository '{}'...", repository.getName());
                    final Page<Branch> branchesPage = stashApi.getRepositoryBranches(projectKey, repository.getSlug());
                    for (final Branch branch : branchesPage.getValues()) {
                        log.info("Cleaning branch '{}'...", branch.getDisplayId());
                        final DeleteBranch deleteBranch = new DeleteBranch();
                        deleteBranch.setName(branch.getId());
                        deleteBranch.setDryRun(false);
                        final Page<PullRequest> pullRequestsPage = stashApi.getPullRequests(projectKey, repository.getSlug());
                        for (final PullRequest pullRequest : pullRequestsPage.getValues()) {
                            if (pullRequest.isOpen()) {
                                log.info("Cleaning pullRequest '{}'...", pullRequest.getId());
                                stashApi.declinePullRequest(projectKey, repository.getSlug(), pullRequest.getId(), pullRequest.getVersion());
                            }
                        }
                        if (!branch.getId().equals("refs/heads/master")) {
                            log.info("Deleting branch '{}'...", branch.getDisplayId());
                            stashApi.deleteBranch(projectKey, repository.getSlug(), deleteBranch);
                        }
                    }
                    // Delete repository
                    log.info("Deleting repository '{}'...", repository.getName());
                    stashApi.deleteRepository(projectKey, repository.getSlug());
                }
                // Delete project
                log.info("Deleting project '{}'...", project.getName());
                stashApi.deleteProject(projectKey);
                break;
            }
        }

    }

    @Test
    public void testRepositoryIntegration() throws IOException, InterruptedException {

        log.info("Creating project...");
        final Project project = new Project();
        project.setKey(projectKey);
        project.setDescription("Test Project Description");
        project.setName("Test Project");
        stashApi.createProject(project);

        log.info("Creating Repository...");

        Repository testRepository = new Repository();
        testRepository.setName("TestRepository");
        testRepository.setProject(project);
        testRepository = stashApi.createRepository(projectKey, testRepository);
        String repositorySlug = testRepository.getSlug();

        log.info("RepositorySlug is set to: '{}'.", repositorySlug);
        
        // http://admin:password@localhost:7990/scm/tpt/testrepository.git
        log.info("Generating repositoryUrl...");
        final String placeholderUrl = "%s%s:%s@%s/scm/%s/%s.git";
        final String repositoryUrl = String.format(placeholderUrl, gitProtocol, userName, password, url, projectKey.toLowerCase(), repositorySlug);

        // Prep target folder
        log.info("Preparing build folder...");
        final File gitTestDir = new File("./build/tmp/git");
        gitTestDir.mkdirs();
        final File repositoryDir = new File(gitTestDir, repositorySlug);
        FileUtils.deleteDirectory(repositoryDir);

        log.info("Performing git clone...");
        Assert.assertEquals(0, new ProcessBuilder("git", "clone", repositoryUrl, repositorySlug).directory(gitTestDir).start().waitFor());

        log.info("Creating a file...");
        File firstFile = new File(repositoryDir, "firstFile.txt");
        FileUtils.write(firstFile, "Harry\nPotter\n");
        
        log.info("Adding all files to git...");
        Assert.assertEquals(0, new ProcessBuilder("git", "add", "--all").directory(repositoryDir).start().waitFor());

        log.info("Committing changes...");
        Assert.assertEquals(0, new ProcessBuilder("git", "commit", "-m", "Adding new file").directory(repositoryDir).start().waitFor());

        log.info("Pushing commits to repository...");
        Assert.assertEquals(0, new ProcessBuilder("git", "push", "origin", "master").directory(repositoryDir).start().waitFor());
        
        log.info("Fetching commits...");
        Page<Commit> commitPage = stashApi.getCommits(projectKey, repositorySlug);
        List<Commit> commits = commitPage.getValues();
        Assert.assertEquals(1, commits.size());
        Commit commit = commits.get(0);
        String commitId = commit.getId();
        
        log.info("Checking that the file is in the repo");
        LinesPage linesPage = stashApi.browse(projectKey, repositorySlug, "firstFile.txt", commitId);
        Assert.assertEquals(2, linesPage.getSize());
        List<Line> lines = linesPage.getLines();
        Assert.assertEquals("Harry", lines.get(0).getText());
        Assert.assertEquals("Potter", lines.get(1).getText());
        
        log.info("Fetching the defaultBranch...");
        final Branch defaultBranch = stashApi.getRepositoryDefaultBranch(projectKey, repositorySlug);

        log.info("Creating a new branch from the defaultbranch...");
        final CreateBranch createBranch = new CreateBranch();
        createBranch.setName(branchName);
        createBranch.setStartPoint(defaultBranch.getId());
        final Branch createdBranch = stashApi.createBranch(projectKey, repositorySlug, createBranch);
        Assert.assertNotNull(createdBranch);
        Assert.assertEquals(featureBranchId, createdBranch.getId());

        log.info("Validating if the branch exists...");
        final Page<Branch> branchPage = stashApi.getRepositoryBranches(projectKey, repositorySlug);

        Branch foundBranch = null;
        for (final Branch branch : branchPage.getValues()) {
            if (branch.getId().equals(featureBranchId)) {
                foundBranch = branch;
                break;
            }
        }
        Assert.assertNotNull(foundBranch);
        Assert.assertEquals(featureBranchId, foundBranch.getId());

        log.info("Fetch the latest changes locally, including remotely created branch...");
        Assert.assertEquals(0, new ProcessBuilder("git", "fetch").directory(repositoryDir).start().waitFor());

        log.info("Checking out newly fetched branch...");
        Assert.assertEquals(0, new ProcessBuilder("git", "checkout", branchName).directory(repositoryDir).start().waitFor());

        log.info("Creating another file...");
        Assert.assertNotNull(Files.createFile(new File(repositoryDir, "secondFile.txt").toPath()));

        log.info("Adding all changed files to git...");
        Assert.assertEquals(0, new ProcessBuilder("git", "add", "--all").directory(repositoryDir).start().waitFor());

        log.info("Committing changes...");
        Assert.assertEquals(0, new ProcessBuilder("git", "commit", "-m", "Adding new file").directory(repositoryDir).start().waitFor());

        log.info("Committing changes to featurebranch...");
        Assert.assertEquals(0, new ProcessBuilder("git", "push").directory(repositoryDir).start().waitFor());

        log.info("Creating a new pullRequest");
        PullRequest pullRequest = new PullRequest();
        pullRequest.setTitle("Integration test pullrequest title");
        pullRequest.setDescription("Integration test pull request description");
        pullRequest.setState(PullRequestState.OPEN);
        pullRequest.setOpen(true);
        pullRequest.setClosed(false);

        final Ref fromRef = new Ref();
        fromRef.setId(featureBranchId);
        fromRef.setRepository(testRepository);
        pullRequest.setFromRef(fromRef);

        final Ref toRef = new Ref();
        toRef.setId(defaultBranch.getId());
        toRef.setRepository(testRepository);
        pullRequest.setToRef(toRef);

        final Set<UserRole> reviewers = new HashSet<UserRole>();
        final UserRole userRole = new UserRole();
        final User user = new User();
        user.setName(userName);
        userRole.setUser(user);
        reviewers.add(userRole);
        pullRequest.setReviewers(reviewers);
        pullRequest = stashApi.createPullRequest(projectKey, repositorySlug, pullRequest);

        log.info("Testing if pullRequest is mergable for pullRequest with id '{}' and version '{}'.", pullRequest.getId(), pullRequest.getVersion());
        final PullRequest mergeablePullRequest = stashApi.testPullRequestMergable(projectKey, repositorySlug, pullRequest.getId());
        Assert.assertTrue(mergeablePullRequest.isCanMerge());

        log.info("Merging pullRequest with id '{}' and version '{}'.", pullRequest.getId(), pullRequest.getVersion());
        pullRequest = stashApi.mergePullRequest(projectKey, repositorySlug, pullRequest.getId(), pullRequest.getVersion());
        Assert.assertEquals(PullRequestState.MERGED, pullRequest.getState());
    }

}
