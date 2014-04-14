package com.bol.cd.stash.parser;

import com.atlassian.stash.rest.client.api.entity.Branch;
import com.bol.cd.stash.api.PullRequest;
import com.google.common.base.Function;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PullRequestParser implements Function<JsonElement, PullRequest> {
    @Override
    public PullRequest apply(final JsonElement json) {
        JsonObject jsonObject = json.getAsJsonObject();
        return new PullRequest(jsonObject.get("id").getAsString(), jsonObject.get("version").getAsInt());
    }
}
