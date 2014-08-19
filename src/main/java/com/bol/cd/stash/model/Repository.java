package com.bol.cd.stash.model;


import java.io.Serializable;

public class Repository implements Serializable {
    private static final long serialVersionUID = 1L;
    private String slug;
    private String name;
    private Project project;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Repository that = (Repository) o;

        if (project != null ? !project.equals(that.project) : that.project != null) return false;
        if (slug != null ? !slug.equals(that.slug) : that.slug != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = slug != null ? slug.hashCode() : 0;
        result = 31 * result + (project != null ? project.hashCode() : 0);
        return result;
    }
}
