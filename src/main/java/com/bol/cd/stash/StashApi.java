package com.bol.cd.stash;

import com.bol.cd.stash.model.*;
import com.bol.cd.stash.request.*;

import javax.ws.rs.*;
import java.util.Map;

/**
 * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-branch-utils-rest.html
 * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-rest.html
 * https://developer.atlassian.com/static/rest/stash/3.2.2/stash-rest.html#idp2039824
 */
//@formatter:off
public interface StashApi {

    @GET
    @Path("/rest/api/1.0/projects")
    public Page<Project> getProjects();

    @POST
    @Path("/rest/api/1.0/projects")
    public Project createProject(Project project);

    @DELETE
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}")
    public void deleteRepository(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}")
    public Project getProject(
            @PathParam("projectKey") String projectKey
    );

    @DELETE
    @Path("/rest/api/1.0/projects/{projectKey}")
    public void deleteProject(
            @PathParam("projectKey") String projectKey
    );

    @PUT
    @Path("/rest/api/1.0/projects/{projectKey}/permissions/groups")
    public void grantGroupPermission(
            @PathParam("projectKey") String projectKey,
            @QueryParam("name") String groupName,
            @QueryParam("permission") String permission
    );

    @DELETE
    @Path("/rest/api/1.0/projects/{projectKey}/permissions/groups")
    public void revokeGroupPermission(
            @PathParam("projectKey") String projectKey,
            @QueryParam("name") String groupName
    );

    @PUT
    @Path("/rest/api/1.0/projects/{projectKey}/permissions/users")
    public void grantUserPermission(
            @PathParam("projectKey") String projectKey,
            @QueryParam("name") String userName,
            @QueryParam("permission") String permission
    );

    @DELETE
    @Path("/rest/api/1.0/projects/{projectKey}/permissions/users")
    public void revokeUserPermission(
            @PathParam("projectKey") String projectKey,
            @QueryParam("name") String userName
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos")
    public Page<Repository> getRepositories(
            @PathParam("projectKey") String projectKey
    );

    @POST
    @Path("/rest/api/1.0/projects/{projectKey}/repos")
    public Repository createRepository(
            @PathParam("projectKey") String projectKey,
            Repository repository
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}")
    public Repository getRepository(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
    public Page<Branch> getRepositoryBranches(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
    public Page<Branch> getRepositoryBranches(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @QueryParam("limit") int limit,
            @QueryParam("start") int start
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
    public Page<Branch> getRepositoryBranches(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @QueryParam("base") String base,
            @QueryParam("details") boolean details,
            @QueryParam("filterText") String filterText,
            @QueryParam("orderBy") String orderBy,
            @QueryParam("limit") int limit,
            @QueryParam("start") int start
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/branches/default")
    public Branch getRepositoryDefaultBranch(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug
    );

    @POST
    @Path("/rest/branch-utils/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
    public Branch createBranch(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            CreateBranch createBranch
    );

    @DELETE
    @Path("/rest/branch-utils/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
    public void deleteBranch(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            DeleteBranch deleteBranch
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/browse/{path}")
    public LinesPage browse(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("path") String path,
            @QueryParam("at") String at
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/commits")
    public Page<Commit> getCommits(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/commits/{commitId}")
    public Commit getCommit(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("commitId") String commitId
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/compare/commits")
    public Page<ComparedChange> getComparedCommits(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @QueryParam("from") String from,
            @QueryParam("to") String to
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests")
    public Page<PullRequest> getPullRequests(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests")
    public Page<PullRequest> getPullRequests(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @QueryParam("at") String atBranch,
            @QueryParam("state") PullRequestState state
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests")
    public Page<PullRequest> getPullRequests(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @QueryParam("direction") PullRequestDirection direction,
            @QueryParam("at") String atBranch,
            @QueryParam("state") PullRequestState state,
            @QueryParam("order") PullRequestSortOrder order
    );

    @POST
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests")
    public PullRequest createPullRequest(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            PullRequest pullRequest
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/merge")
    public PullRequest testPullRequestMergable(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("pullRequestId") String pullRequestId
    );

    @POST
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/merge")
    public PullRequest mergePullRequest(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("pullRequestId") String pullRequestId,
            @QueryParam("version") int version
    );

    @POST
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/decline")
    public void declinePullRequest(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("pullRequestId") String pullRequestId,
            @QueryParam("version") int version
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}")
    public RepositoryHook getRepositoryHook(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("hookKey") String hookKey
    );

    @PUT
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}/enabled")
    public void enableRepositoryHook(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("hookKey") String hookKey
    );

    @DELETE
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}/enabled")
    public void disableRepositoryHook(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("hookKey") String hookKey
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}/settings")
    public Map<String, String> getRepositoryHookSettings(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("hookKey") String hookKey
    );

    @PUT
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/settings/hooks/{hookKey}/settings")
    public void setRepositoryHookSettings(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug,
            @PathParam("hookKey") String hookKey,
            Map<String, String> settings
    );
}
//@formatter:on
