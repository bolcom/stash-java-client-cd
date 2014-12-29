package com.bol.cd.stash.model.ssh;

import com.bol.cd.stash.model.Repository;
import com.bol.cd.stash.model.RepositoryPermission;

import java.io.Serializable;

public class RepositoryAccessKey implements Serializable {
    private static final long serialVersionUID = 3219647025618218462L;
    private SshKey key;
    private Repository repository;
    private RepositoryPermission permission;

    public SshKey getKey() {
        return key;
    }

    public void setKey(SshKey key) {
        this.key = key;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public RepositoryPermission getPermission() {
        return permission;
    }

    public void setPermission(RepositoryPermission permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RepositoryAccessKey that = (RepositoryAccessKey) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (permission != that.permission) return false;
        if (repository != null ? !repository.equals(that.repository) : that.repository != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (repository != null ? repository.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RepositoryAccessKey{");
        sb.append("key=").append(key);
        sb.append(", repository=").append(repository);
        sb.append(", permission=").append(permission);
        sb.append('}');
        return sb.toString();
    }
}
