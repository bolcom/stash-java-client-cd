package com.bol.cd.stash.request;

import java.io.Serializable;

/**
 * <pre>
 * {
 *     "name": "feature/my-feature-branch",
 *     "startPoint": "refs/heads/master"
 * }
 * </pre>
 */
public class CreateBranch implements Serializable {
    private static final long serialVersionUID = -4105090098128586601L;
    private String name;
    private String startPoint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateBranch that = (CreateBranch) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startPoint != null ? !startPoint.equals(that.startPoint) : that.startPoint != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (startPoint != null ? startPoint.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CreateBranch{");
        sb.append("name='").append(name).append('\'');
        sb.append(", startPoint='").append(startPoint).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
