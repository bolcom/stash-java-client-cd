package com.bol.cd.stash.api;

public class PullRequest {
    private final String id;

    private final int version;

    public PullRequest(String id, int version) {
        this.id = id;
        this.version = version;

    }

    public String getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

}
