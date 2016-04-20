package com.bol.cd.stash.model;

/**
 * {
 * "force": "true",
 * "message": "A new release tag",
 * "name": "release-tag",
 * "startPoint": "refs/heads/master",
 * "type": "ANNOTATED"
 * }
 */
public class Tag {
    private boolean force = false;
    private String message;
    private String name;
    private String startPoint;
    private String type  = "ANNOTATED";


    public Tag() {
    }

    public Tag(String message, String name, String startPoint) {
        this.message = message;
        this.name = name;
        this.startPoint = startPoint;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "message='" + message + '\'' +
                ", name='" + name + '\'' +
                ", startPoint='" + startPoint + '\'' +
                '}';
    }
}
