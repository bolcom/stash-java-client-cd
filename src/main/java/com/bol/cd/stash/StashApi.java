package com.bol.cd.stash;

import com.bol.cd.stash.model.*;
import com.bol.cd.stash.request.*;

import javax.ws.rs.*;

/**
 * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-branch-utils-rest.html
 * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-rest.html
 */
//@formatter:off
public interface StashApi {

    @GET
    @Path("/rest/api/1.0/projects")
    public Page<Project> getProjects();

    @POST
    @Path("/rest/api/1.0/projects")
    public Project createProject(Project project);

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}")
    public Repository getRepository(
            @PathParam("projectKey") String projectKey,
            @PathParam("repositorySlug") String repositorySlug
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos")
    public Page<Repository> getRepositories(
            @PathParam("projectKey") final String projectKey
    );

    @POST
    @Path("/rest/api/1.0/projects/{projectKey}/repos")
    public Repository createRepository(
            @PathParam("projectKey") final String projectKey,
            final Repository repository
    );

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
            @PathParam("projectKey") final String projectKey,
            @PathParam("repositorySlug") final String repositorySlug,
            @PathParam("pullRequestId") final String pullRequestId
    );

    @POST
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/merge")
    public PullRequest mergePullRequest(
            @PathParam("projectKey") final String projectKey,
            @PathParam("repositorySlug") final String repositorySlug,
            @PathParam("pullRequestId") final String pullRequestId,
            @QueryParam("version") int version
    );

    @POST
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/decline")
    public void declinePullRequest(
            @PathParam("projectKey") final String projectKey,
            @PathParam("repositorySlug") final String repositorySlug,
            @PathParam("pullRequestId") final String pullRequestId,
            @QueryParam("version") int version
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/browse/{path}")
    public LinesPage browse(
            @PathParam("projectKey") final String projectKey,
            @PathParam("repositorySlug") final String repositorySlug,
            @PathParam("path") final String path,
            @QueryParam("at") String at
    );

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/commits")
    public Page<Commit> getCommits(
            @PathParam("projectKey") final String projectKey,
            @PathParam("repositorySlug") final String repositorySlug
    );
}
//@formatter:on
