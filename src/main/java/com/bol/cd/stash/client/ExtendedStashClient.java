package com.bol.cd.stash.client;

import com.atlassian.stash.rest.client.api.StashClient;
import com.atlassian.stash.rest.client.api.StashException;
import com.atlassian.stash.rest.client.core.http.HttpMethod;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface ExtendedStashClient extends StashClient {
    public JsonElement doRestCall(String requestUrl, HttpMethod methodType, JsonObject requestJson, boolean anonymousCall) throws StashException;

    public BranchStashClientImpl getBranchStashClient();

    public PullRequestStashClientImpl getPullRequestStashClient();
}
