package com.bol.cd.stash.request;

/**
 * <pre>
 * {
 *     "name": "feature/my-feature-branch",
 *     "startPoint": "refs/heads/master"
 * }
 * </pre>
 */
public class CreateBranch {

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
}
