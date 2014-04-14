package com.bol.cd.stash.client;

import com.atlassian.stash.rest.client.api.StashException;
import com.atlassian.stash.rest.client.core.StashClientImpl;
import com.atlassian.stash.rest.client.core.http.HttpExecutor;
import com.atlassian.stash.rest.client.core.http.HttpMethod;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class ExtendedStashClientImpl extends StashClientImpl implements ExtendedStashClient {

    protected BranchStashClientImpl branchStashClientImpl;
    protected PullRequestStashClientImpl pullRequestStashClientImpl;

    public ExtendedStashClientImpl(HttpExecutor httpExecutor) {
        super(httpExecutor);
        branchStashClientImpl = new BranchStashClientImpl(this);
        pullRequestStashClientImpl = new PullRequestStashClientImpl(this);
    }

    public JsonElement doRestCall(String requestUrl, HttpMethod methodType, JsonObject requestJson, boolean anonymousCall) throws StashException {
        return super.doRestCall(requestUrl, methodType, requestJson, anonymousCall);
    }

    public BranchStashClientImpl getBranchStashClient() {
        return branchStashClientImpl;
    }

    public PullRequestStashClientImpl getPullRequestStashClient() {
        return pullRequestStashClientImpl;
    }
}
