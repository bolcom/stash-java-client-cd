package com.bol.cd.stash.model;

import java.io.Serializable;

/**
 * Defines a change of a commit as defined by the:
 * https://developer.atlassian.com/static/rest/stash/3.2.2/stash-rest.html#idp2027680
 */
public class ComparedChange implements Serializable {
    private static final long serialVersionUID = -5505340751544312710L;
    private String id;
    private String displayId;
    private User author;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComparedChange that = (ComparedChange) o;

        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ComparedChange{");
        sb.append("id='").append(id).append('\'');
        sb.append(", displayId='").append(displayId).append('\'');
        sb.append(", author=").append(author);
        sb.append('}');
        return sb.toString();
    }
}
