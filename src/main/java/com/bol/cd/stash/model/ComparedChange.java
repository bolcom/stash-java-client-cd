package com.bol.cd.stash.model;

/**
 * Defines a change of a commit as defined by the:
 * https://developer.atlassian.com/static/rest/stash/3.2.2/stash-rest.html#idp2027680
 */
public class ComparedChange {

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
}