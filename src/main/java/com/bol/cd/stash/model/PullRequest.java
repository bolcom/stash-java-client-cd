package com.bol.cd.stash.model;

import com.bol.cd.stash.request.PullRequestState;

import java.io.Serializable;
import java.util.Set;

public class PullRequest implements Serializable {
    private static final long serialVersionUID = 4042708210811963920L;
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

    public boolean isCanMerge() {
        return canMerge;
    }

    public void setCanMerge(boolean canMerge) {
        this.canMerge = canMerge;
    }

    public boolean isConflicted() {
        return conflicted;
    }

    public void setConflicted(boolean conflicted) {
        this.conflicted = conflicted;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PullRequest that = (PullRequest) o;

        if (version != that.version) return false;
        if (fromRef != null ? !fromRef.equals(that.fromRef) : that.fromRef != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (toRef != null ? !toRef.equals(that.toRef) : that.toRef != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + version;
        result = 31 * result + (fromRef != null ? fromRef.hashCode() : 0);
        result = 31 * result + (toRef != null ? toRef.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PullRequest{");
        sb.append("id='").append(id).append('\'');
        sb.append(", version=").append(version);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", state=").append(state);
        sb.append(", open=").append(open);
        sb.append(", closed=").append(closed);
        sb.append(", canMerge=").append(canMerge);
        sb.append(", conflicted=").append(conflicted);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append(", fromRef=").append(fromRef);
        sb.append(", toRef=").append(toRef);
        sb.append(", author=").append(author);
        sb.append(", reviewers=").append(reviewers);
        sb.append(", participants=").append(participants);
        sb.append('}');
        return sb.toString();
    }
}
