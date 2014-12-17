package com.bol.cd.stash.model;

import java.io.Serializable;

public class UserRole implements Serializable {
    private static final long serialVersionUID = 4480059220429995841L;
    private User user;
    private Role role;
    private boolean approved;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (approved != userRole.approved) return false;
        if (role != userRole.role) return false;
        if (user != null ? !user.equals(userRole.user) : userRole.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (approved ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserRole{");
        sb.append("user=").append(user);
        sb.append(", role=").append(role);
        sb.append(", approved=").append(approved);
        sb.append('}');
        return sb.toString();
    }
}
