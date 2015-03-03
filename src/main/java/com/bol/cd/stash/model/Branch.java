package com.bol.cd.stash.model;

import java.io.Serializable;

public class Branch implements Serializable {
    private static final long serialVersionUID = 1965752533172073272L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        if (isDefault != branch.isDefault) return false;
        if (id != null ? !id.equals(branch.id) : branch.id != null) return false;
        if (latestChangeset != null ? !latestChangeset.equals(branch.latestChangeset) : branch.latestChangeset != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (latestChangeset != null ? latestChangeset.hashCode() : 0);
        result = 31 * result + (isDefault ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "displayId='" + displayId + '\'' +
                ", latestChangeset='" + latestChangeset + '\'' +
                '}';
    }
}
