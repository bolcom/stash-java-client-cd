package com.bol.cd.stash.model.ssh;

import com.bol.cd.stash.model.Project;
import com.bol.cd.stash.model.ProjectPermission;

import java.io.Serializable;

public class ProjectAccessKey implements Serializable {
    private static final long serialVersionUID = 1287451339003455808L;
    private SshKey key;
    private Project project;
    private ProjectPermission permission;

    public SshKey getKey() {
        return key;
    }

    public void setKey(SshKey key) {
        this.key = key;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectPermission getPermission() {
        return permission;
    }

    public void setPermission(ProjectPermission permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectAccessKey that = (ProjectAccessKey) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (permission != that.permission) return false;
        if (project != null ? !project.equals(that.project) : that.project != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (project != null ? project.hashCode() : 0);
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProjectAccessKey{");
        sb.append("key=").append(key);
        sb.append(", project=").append(project);
        sb.append(", permission=").append(permission);
        sb.append('}');
        return sb.toString();
    }
}
