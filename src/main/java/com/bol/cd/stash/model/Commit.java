package com.bol.cd.stash.model;

import java.io.Serializable;
import java.util.List;

public class Commit implements Serializable {
    private static final long serialVersionUID = 6908773966876863960L;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commit commit = (Commit) o;

        if (id != null ? !id.equals(commit.id) : commit.id != null) return false;
        if (message != null ? !message.equals(commit.message) : commit.message != null) return false;
        if (parents != null ? !parents.equals(commit.parents) : commit.parents != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (parents != null ? parents.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Commit{");
        sb.append("id='").append(id).append('\'');
        sb.append(", displayId='").append(displayId).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", parents=").append(parents);
        sb.append('}');
        return sb.toString();
    }
}
