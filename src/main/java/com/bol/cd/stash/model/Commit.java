package com.bol.cd.stash.model;

import java.util.List;

public class Commit {

    private String id;
    private String displayId;
    private String message;
    private List<Commit> parents;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Commit> getParents() {
        return parents;
    }

    public void setParents(List<Commit> parents) {
        this.parents = parents;
    }
}
