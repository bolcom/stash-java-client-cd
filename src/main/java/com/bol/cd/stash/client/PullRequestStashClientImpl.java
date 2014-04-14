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

public class PullRequestStashClientImpl {

    private ExtendedStashClient extendedStashClient;

    public PullRequestStashClientImpl(ExtendedStashClient extendedStashClient) {
        this.extendedStashClient = extendedStashClient;
    }

    /**
     * POST /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}/merge?version
     */
    public void mergePullRequest(String projectKey, String repositorySlug, String pullRequestId) {
        PullRequest pullRequest = getPullRequest(projectKey, repositorySlug, pullRequestId);
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests/%s/merge?version=", projectKey, repositorySlug, pullRequestId);
        requestUrl += pullRequest.getVersion();
        JsonElement jsonElement = extendedStashClient.doRestCall(requestUrl, POST, null, false);
        System.out.println(jsonElement);
    }

    /**
     * GET /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests/{pullRequestId}
     * 
     * @param projectKey
     * @param repositorySlug
     * @param pullRequestId
     * @return
     */

    public PullRequest getPullRequest(String projectKey, String repositorySlug, String pullRequestId) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests/%s", projectKey, repositorySlug, pullRequestId);
        JsonElement jsonElement = extendedStashClient.doRestCall(requestUrl, GET, null, false);
        return pullRequestParser().apply(jsonElement);
    }

    /**
     * https://developer.atlassian.com/static/rest/stash/2.12.1/stash-rest.html <br/>
     * POST /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests
     * 
     * @return
     */
    public PullRequest createPullRequestToDefaultBranch(String projectKey, String repositorySlug, String name, String title, String description,
            String[] reviewers) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests", projectKey, repositorySlug);
        Repository repository = extendedStashClient.getRepository(projectKey, repositorySlug);

        Branch defaultBranch = extendedStashClient.getRepositoryDefaultBranch(projectKey, repositorySlug);
        Optional<Branch> optionalBranch = extendedStashClient.getBranchStashClient().getRepositoryBranch(projectKey, repositorySlug, name);
        if (!optionalBranch.isPresent()) {
            throw new IllegalStateException("No branch for branch with name '" + name + "'. ");
        }
        Branch fromBranch = optionalBranch.get();
        Ref fromRef = new Ref(fromBranch.getId(), repository);
        Ref toRef = new Ref(defaultBranch.getId(), repository);
        PullRequestRequest pullRequestRequest = new PullRequestRequest(title, description, fromRef, toRef, reviewers);
        JsonElement jsonElement = extendedStashClient.doRestCall(requestUrl, POST, pullRequestRequest.toJson(), false);
        System.out.println(jsonElement);
        return pullRequestParser().apply(jsonElement);

    }

    /**
     * GET /rest/api/1.0/projects/{projectKey}/repos/{repositorySlug}/pull-requests
     * 
     * @param projectKey
     * @param repositorySlug
     */
    public Page<PullRequest> getPullRequests(String projectKey, String repositorySlug) {
        String requestUrl = String.format("/rest/api/1.0/projects/%s/repos/%s/pull-requests", projectKey, repositorySlug);
        JsonElement jsonElement = extendedStashClient.doRestCall(requestUrl, GET, null, false);
        return pageParser(pullRequestParser()).apply(jsonElement);
    }

}
