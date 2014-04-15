package com.bol.cd.stash;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.bol.cd.stash.model.Branch;
import com.bol.cd.stash.model.Page;
import com.bol.cd.stash.request.DeleteBranch;

/**
 * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-branch-utils-rest.html
 * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-rest.html
 * 
 * @author ckramer
 */
public interface StashApi {

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
    public Page<Branch> getRepositoryBranches(@PathParam("projectKey") String projectKey, @PathParam("repositorySlug") String repositorySlug,
            @QueryParam("limit") int limit, @QueryParam("start") int start);

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
    public Page<Branch> getRepositoryBranches(@PathParam("projectKey") String projectKey, @PathParam("repositorySlug") String repositorySlug,
            @QueryParam("base") String base, @QueryParam("details") boolean details, @QueryParam("filterText") String filterText,
            @QueryParam("orderBy") String orderBy, @QueryParam("limit") int limit, @QueryParam("start") int start);

    @GET
    @Path("/rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/branches/default")
    public Branch getRepositoryDefaultBranch(@PathParam("projectKey") String projectKey, @PathParam("repositorySlug") String repositorySlug);

    @POST
    @Path("/rest/branch-utils/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
    public Branch createBranch(@PathParam("projectKey") String projectKey, @PathParam("repositorySlug") String repositorySlug);

    @DELETE
    @Path("/rest/branch-utils/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
    public void deleteBranch(@PathParam("projectKey") String projectKey, @PathParam("repositorySlug") String repositorySlug, DeleteBranch deleteBranch);

}
