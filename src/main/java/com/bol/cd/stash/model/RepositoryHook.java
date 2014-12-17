package com.bol.cd.stash.model;

import java.io.Serializable;

public class RepositoryHook implements Serializable {
    private static final long serialVersionUID = 4392688611832720499L;
    public boolean enabled;
    public boolean configured;
    public RepositoryHookDetails details;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isConfigured() {
        return configured;
    }

    public void setConfigured(boolean configured) {
        this.configured = configured;
    }

    public RepositoryHookDetails getDetails() {
        return details;
    }

    public void setDetails(RepositoryHookDetails details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepositoryHook that = (RepositoryHook) o;

        if (configured != that.configured) return false;
        if (enabled != that.enabled) return false;
        if (!details.equals(that.details)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (enabled ? 1 : 0);
        result = 31 * result + (configured ? 1 : 0);
        result = 31 * result + details.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RepositoryHook{");
        sb.append("enabled=").append(enabled);
        sb.append(", configured=").append(configured);
        sb.append(", details=").append(details);
        sb.append('}');
        return sb.toString();
    }
}


