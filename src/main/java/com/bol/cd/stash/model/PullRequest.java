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
    private boolean canMerge;
    private boolean conflicted;
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

    public void setId(final String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public PullRequestState getState() {
        return state;
    }

    public void setState(final PullRequestState state) {
        this.state = state;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(final boolean open) {
        this.open = open;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(final boolean closed) {
        this.closed = closed;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final long createdDate) {
        this.createdDate = createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(final long updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Ref getFromRef() {
        return fromRef;
    }

    public void setFromRef(final Ref fromRef) {
        this.fromRef = fromRef;
    }

    public Ref getToRef() {
        return toRef;
    }

    public void setToRef(final Ref toRef) {
        this.toRef = toRef;
    }

    public UserRole getAuthor() {
        return author;
    }

    public void setAuthor(final UserRole author) {
        this.author = author;
    }

    public Set<UserRole> getReviewers() {
        return reviewers;
    }

    public void setReviewers(final Set<UserRole> reviewers) {
        this.reviewers = reviewers;
    }

    public Set<UserRole> getParticipants() {
        return participants;
    }

    public void setParticipants(final Set<UserRole> participants) {
        this.participants = participants;
    }

    public boolean isCanMerge() {
        return canMerge;
    }

    public void setCanMerge(final boolean canMerge) {
        this.canMerge = canMerge;
    }

    public boolean isConflicted() {
        return conflicted;
    }

    public void setConflicted(final boolean conflicted) {
        this.conflicted = conflicted;
    }
}
