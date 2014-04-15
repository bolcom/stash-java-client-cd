package com.bol.cd.stash.model;


import com.bol.cd.stash.request.PullRequestState;

import java.util.Set;

public class PullRequest {
    private String id;
    private int version;
    private String title;
    private String description;
    private PullRequestState state;
    private boolean open;
    private boolean closed;
    private long createdDate;
    private long updatedDate;
    private Ref fromRef;
    private Ref toRef;
    private UserRole author;
    private Set<UserRole> reviewers;
    private Set<UserRole> participants;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PullRequestState getState() {
        return state;
    }

    public void setState(PullRequestState state) {
        this.state = state;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Ref getFromRef() {
        return fromRef;
    }

    public void setFromRef(Ref fromRef) {
        this.fromRef = fromRef;
    }

    public Ref getToRef() {
        return toRef;
    }

    public void setToRef(Ref toRef) {
        this.toRef = toRef;
    }

    public UserRole getAuthor() {
        return author;
    }

    public void setAuthor(UserRole author) {
        this.author = author;
    }

    public Set<UserRole> getReviewers() {
        return reviewers;
    }

    public void setReviewers(Set<UserRole> reviewers) {
        this.reviewers = reviewers;
    }

    public Set<UserRole> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<UserRole> participants) {
        this.participants = participants;
    }
}
