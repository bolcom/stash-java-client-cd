package com.bol.cd.stash.entity;

import com.google.gson.JsonObject;

/**
 * <pre>
 * {
 *   "name": "feature/my-feature-branch",
 *   "startPoint": "refs/heads/master"
 * }
 * </pre>
 * 
 * @author ckramer
 */
public class BranchRequest {

    private final String name;
    private final String startPoint;

    public BranchRequest(final String name, final String startPoint) {
        this.name = name;
        this.startPoint = startPoint;

    }

    public JsonObject toJson() {

        JsonObject keyPayload = new JsonObject();
        keyPayload.addProperty("name", name);
        keyPayload.addProperty("startPoint", startPoint);
        return keyPayload;
    }
}
