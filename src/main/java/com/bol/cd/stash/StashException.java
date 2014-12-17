package com.bol.cd.stash;

public class StashException extends RuntimeException {
    private int status;

    public StashException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public StashException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
