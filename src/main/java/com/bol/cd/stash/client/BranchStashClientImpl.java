package com.bol.cd.stash.client;

import static com.atlassian.stash.rest.client.core.http.HttpMethod.POST;
import static com.atlassian.stash.rest.client.core.parser.Parsers.branchParser;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.atlassian.stash.rest.client.api.entity.Branch;
import com.atlassian.stash.rest.client.api.entity.Page;
import com.atlassian.stash.rest.client.core.StashClientImpl;
import com.bol.cd.stash.entity.BranchRequest;
import com.google.gson.JsonElement;

public class BranchStashClientImpl {

    private static final Logger log = Logger.getLogger(BranchStashClientImpl.class);

    private ExtendedStashClient extendedStashClient;

    public BranchStashClientImpl(ExtendedStashClient extendedStashClient) {
        this.extendedStashClient = extendedStashClient;
    }

    /**
     * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-branch-utils-rest.html#idp44976 <br/>
     * POST /rest/branch-utils/1.0/projects/{projectKey}/repos/{repositorySlug}/branches
     * 
     * @param projectKey
     * @param repositorySlug
     * @param branchName
     */
    public Branch createBranchFromDefaultBranch(String projectKey, String repositorySlug, String name) {
        Branch defaultBranch = extendedStashClient.getRepositoryDefaultBranch(projectKey, repositorySlug);
        return createBranchFromBranch(projectKey, repositorySlug, name, defaultBranch.getId());
    }

    /**
     * @param projectKey
     * @param repositorySlug
     * @param name
     * @return
     */
    public Branch createBranchFromMaster(String projectKey, String repositorySlug, String name) {
        return createBranchFromBranch(projectKey, repositorySlug, name, "refs/heads/master");
    }

    /**
     * @param projectKey
     * @param repositorySlug
     * @param name
     * @param startPoint
     * @return
     */
    public Branch createBranchFromBranch(String projectKey, String repositorySlug, String name, String startPoint) {
        String requestUrl = String.format("/rest/branch-utils/1.0/projects/%s/repos/%s/branches", projectKey, repositorySlug);
        BranchRequest branchRequest = new BranchRequest(name, startPoint);
        JsonElement jsonElement = extendedStashClient.doRestCall(requestUrl, POST, branchRequest.toJson(), false);
        return branchParser().apply(jsonElement);
    }

    /**
     * @param projectKey
     * @param repositorySlug
     * @param id
     * @return
     */
    public Optional<Branch> getRepositoryBranchBId(String projectKey, String repositorySlug, String id) {
        log.info("Finding repositoryBranch with displayId: " + id);
        Page<Branch> branchesPage = extendedStashClient.getRepositoryBranches(projectKey, repositorySlug, id, 0, 100);
        if (!branchesPage.isLastPage()) {
            throw new IllegalStateException("Too many results returned for displayId '" + id + "'.");
        }
        return branchesPage.getValues().stream().filter(b -> b.getId().equals(id)).findFirst();
    }

}
