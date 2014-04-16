package com.bol.cd.stash.model;

public class Branch {

    private String id;
    private String displayId;
    private String latestChangeset;
    private boolean isDefault;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(final String displayId) {
        this.displayId = displayId;
    }

    public String getLatestChangeset() {
        return latestChangeset;
    }

    public void setLatestChangeset(final String latestChangeset) {
        this.latestChangeset = latestChangeset;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(final boolean isDefault) {
        this.isDefault = isDefault;
    }

}
