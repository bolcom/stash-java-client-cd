package com.bol.cd.stash.request;

/**
 * <pre>
 * {
 *     "name": "/refs/heads/my-branch",
 *     "dryRun": false
 * }
 * </pre>
 */
public class DeleteBranch {

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

}
