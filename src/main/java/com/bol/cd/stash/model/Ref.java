package com.bol.cd.stash.model;

import java.io.Serializable;

public class Ref implements Serializable {
    private static final long serialVersionUID = -2201492261211787178L;
    private String id;
    private String latestChangeset;
    private Repository repository;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLatestChangeset() {
        return latestChangeset;
    }

    public void setLatestChangeset(String latestChangeset) {
        this.latestChangeset = latestChangeset;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ref ref = (Ref) o;

        if (id != null ? !id.equals(ref.id) : ref.id != null) return false;
        if (latestChangeset != null ? !latestChangeset.equals(ref.latestChangeset) : ref.latestChangeset != null)
            return false;
        if (repository != null ? !repository.equals(ref.repository) : ref.repository != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (latestChangeset != null ? latestChangeset.hashCode() : 0);
        result = 31 * result + (repository != null ? repository.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ref{");
        sb.append("id='").append(id).append('\'');
        sb.append(", latestChangeset='").append(latestChangeset).append('\'');
        sb.append(", repository=").append(repository);
        sb.append('}');
        return sb.toString();
    }
}
