package com.bol.cd.stash.client;

import static com.atlassian.stash.rest.client.core.http.HttpMethod.GET;
import static com.atlassian.stash.rest.client.core.http.HttpMethod.POST;
import static com.atlassian.stash.rest.client.core.parser.Parsers.branchParser;
import static com.atlassian.stash.rest.client.core.parser.Parsers.pageParser;
import static com.bol.cd.stash.parser.ExtendedParsers.pullRequestParser;

import java.util.Optional;

import com.atlassian.stash.rest.client.api.entity.Branch;
import com.atlassian.stash.rest.client.api.entity.Page;
import com.atlassian.stash.rest.client.api.entity.Repository;
import com.bol.cd.stash.api.PullRequest;
import com.bol.cd.stash.entity.BranchRequest;
import com.bol.cd.stash.entity.PullRequestRequest;
import com.bol.cd.stash.entity.PullRequestRequest.Ref;
import com.google.gson.JsonElement;

public class BranchStashClientImpl {

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
    public Branch createBranchFromMaster(String projectKey, String repositorySlug, String name) {
        String requestUrl = String.format("/rest/branch-utils/1.0/projects/%s/repos/%s/branches", projectKey, repositorySlug);
        BranchRequest branchRequest = new BranchRequest(name, "refs/heads/master");
        JsonElement jsonElement = extendedStashClient.doRestCall(requestUrl, POST, branchRequest.toJson(), false);
        return branchParser().apply(jsonElement);
    }

    public Optional<Branch> getRepositoryBranch(String projectKey, String repositorySlug, String displayId) {
        Page<Branch> branchesPage = extendedStashClient.getRepositoryBranches(projectKey, repositorySlug, null, 0, 100);
        for (Branch branch : branchesPage.getValues()) {
            System.out.println(branch.getDisplayId());
        }
        return branchesPage.getValues().stream().filter(b -> b.getDisplayId().equals(displayId)).findFirst();
    }

}
