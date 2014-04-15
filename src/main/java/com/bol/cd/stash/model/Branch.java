package com.bol.cd.stash.model;

public class Branch {

    private String id;
    private String displayId;
    private String latestChangeset;
    private boolean isDefault;

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

    public String getLatestChangeset() {
        return latestChangeset;
    }

    public void setLatestChangeset(String latestChangeset) {
        this.latestChangeset = latestChangeset;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

}
