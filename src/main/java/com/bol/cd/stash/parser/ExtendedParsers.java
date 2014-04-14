package com.bol.cd.stash.parser;

import com.bol.cd.stash.api.PullRequest;
import com.google.common.base.Function;
import com.google.gson.JsonElement;

public class ExtendedParsers {
    public static Function<JsonElement, PullRequest> pullRequestParser() {
        return PULL_REQUEST_PARSER;
    }

    private static final PullRequestParser PULL_REQUEST_PARSER = new PullRequestParser();
}
