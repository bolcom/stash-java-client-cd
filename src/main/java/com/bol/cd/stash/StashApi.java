package com.bol.cd.stash;

import java.util.List;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-branch-utils-rest.html
 * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-rest.html
 * 
 * @author ckramer
 */
public interface StashApi {

//    @GET
//    @Path("/rest/branch-utils/1.0/projects/{projectKey}/repos/{repositorySlug}/branches")
//    public List<Branch> getRepositoryBranches(@Named("projectKey") String projectKey, @Named("repositorySlug") String repositorySlug);

    @GET
    @Path("/rest/branch-utils/1.0/projects/{projectKey}/repos/{repositorySlug}/branches/default")
    public Branch getRepositoryDefaultBranch(@Named("projectKey") String projectKey, @Named("repositorySlug") String repositorySlug);

}
