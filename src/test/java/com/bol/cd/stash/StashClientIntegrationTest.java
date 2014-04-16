package com.bol.cd.stash;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bol.cd.stash.model.Branch;
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

@Ignore
public class StashClientIntegrationTest {

    private final static Logger log = LoggerFactory.getLogger(StashClientIntegrationTest.class);
    private final static String gitProtocol = "http://";
    private final static String url = "localhost:7990";
    private final static String userName = "admin";
    private final static String password = "password";
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

        // Create Project
        final Project project = new Project();
        project.setKey(projectKey);
        project.setDescription("Test Project Description");
        project.setName("Test Project");
        stashApi.createProject(project);

        // Create Repository
        Repository testRepository = new Repository();
        testRepository.setName("TestRepository");
        testRepository.setProject(project);
        testRepository = stashApi.createRepository(projectKey, testRepository);

        // http://admin:password@localhost:7990/scm/tpt/testrepository.git
        final String placeholderUrl = "%s%s:%s@%s/scm/%s/%s.git";
        final String repositoryUrl = String.format(placeholderUrl, gitProtocol, userName, password, url, projectKey.toLowerCase(), testRepository.getSlug());

        // Prep target folder
        final File gitTestDir = new File("./build/tmp/git");
        gitTestDir.mkdirs();
        final File repositoryDir = new File(gitTestDir, testRepository.getSlug());
        FileUtils.deleteDirectory(repositoryDir);

        // Perform clone
        Assert.assertEquals(0, new ProcessBuilder("git", "clone", repositoryUrl, testRepository.getSlug()).directory(gitTestDir).start().waitFor());

        // Create file
        Assert.assertNotNull(Files.createFile(new File(repositoryDir, "firstFile.txt").toPath()));

        // Add file(s)
        Assert.assertEquals(0, new ProcessBuilder("git", "add", "--all").directory(repositoryDir).start().waitFor());

        // Commit
        Assert.assertEquals(0, new ProcessBuilder("git", "commit", "-m", "Adding new file").directory(repositoryDir).start().waitFor());

        // Push
        Assert.assertEquals(0, new ProcessBuilder("git", "push", "origin", "master").directory(repositoryDir).start().waitFor());

        // Get default branch
        final Branch defaultBranch = stashApi.getRepositoryDefaultBranch(projectKey, testRepository.getSlug());

        // Create new Branch from default branch
        final CreateBranch createBranch = new CreateBranch();
        createBranch.setName(branchName);
        createBranch.setStartPoint(defaultBranch.getId());
        final Branch createdBranch = stashApi.createBranch(projectKey, testRepository.getSlug(), createBranch);
        Assert.assertNotNull(createdBranch);
        Assert.assertEquals(featureBranchId, createdBranch.getId());

        // Validate the branch exists
        final Page<Branch> branchPage = stashApi.getRepositoryBranches(projectKey, testRepository.getSlug());

        Branch foundBranch = null;
        for (final Branch branch : branchPage.getValues()) {
            if (branch.getId().equals(featureBranchId)) {
                foundBranch = branch;
                break;
            }
        }
        Assert.assertNotNull(foundBranch);
        Assert.assertEquals(featureBranchId, foundBranch.getId());

        // Fetch latest changes
        Assert.assertEquals(0, new ProcessBuilder("git", "fetch").directory(repositoryDir).start().waitFor());

        // Checkout branch
        Assert.assertEquals(0, new ProcessBuilder("git", "checkout", branchName).directory(repositoryDir).start().waitFor());

        // Create file
        Assert.assertNotNull(Files.createFile(new File(repositoryDir, "secondFile.txt").toPath()));

        // Add file
        Assert.assertEquals(0, new ProcessBuilder("git", "add", "--all").directory(repositoryDir).start().waitFor());

        // Commit
        Assert.assertEquals(0, new ProcessBuilder("git", "commit", "-m", "Adding new file").directory(repositoryDir).start().waitFor());

        // Push
        Assert.assertEquals(0, new ProcessBuilder("git", "push").directory(repositoryDir).start().waitFor());

        // Create pullrequest
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
        pullRequest = stashApi.createPullRequest(projectKey, testRepository.getSlug(), pullRequest);

        // Test if it's mergable
        pullRequest = stashApi.testPullRequestMergable(placeholderUrl, testRepository.getSlug(), pullRequest.getId());

        Assert.assertTrue(pullRequest.isCanMerge());
    }

}
