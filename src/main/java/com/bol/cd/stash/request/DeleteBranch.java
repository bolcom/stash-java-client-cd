package com.bol.cd.stash.request;

import java.io.Serializable;

/**
 * <pre>
 * {
 *     "name": "/refs/heads/my-branch",
 *     "dryRun": false
 * }
 * </pre>
 */
public class DeleteBranch implements Serializable {
    private static final long serialVersionUID = -5485937747467607838L;
    private String name;
    private boolean dryRun;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDryRun() {
        return dryRun;
    }

    public void setDryRun(boolean dryRun) {
        this.dryRun = dryRun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteBranch that = (DeleteBranch) o;

        if (dryRun != that.dryRun) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (dryRun ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DeleteBranch{");
        sb.append("name='").append(name).append('\'');
        sb.append(", dryRun=").append(dryRun);
        sb.append('}');
        return sb.toString();
    }
}
