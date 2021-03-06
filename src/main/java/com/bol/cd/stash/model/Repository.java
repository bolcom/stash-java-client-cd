package com.bol.cd.stash.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Repository implements Serializable {
    private static final long serialVersionUID = 3598967315405621898L;
    private String slug;
    private String name;
    private Project project;
    private Map<String, List<Link>> links;

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

    public Map<String, List<Link>> getLinks() {
        return links;
    }

    public void setLinks(Map<String, List<Link>> links) {
        this.links = links;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Repository that = (Repository) o;

        if (project != null ? !project.equals(that.project) : that.project != null)
            return false;
        if (slug != null ? !slug.equals(that.slug) : that.slug != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = slug != null ? slug.hashCode() : 0;
        result = 31 * result + (project != null ? project.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String projectKey = null;
        if (project != null) {
            projectKey = project.getKey();
        }

        return "Repository{" + "project=" + projectKey + ", slug='" + slug + '\'' + '}';
    }
}
