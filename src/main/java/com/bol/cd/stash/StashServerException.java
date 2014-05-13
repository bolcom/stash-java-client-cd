package com.bol.cd.stash;

public class StashServerException extends StashException {
    public StashServerException(int status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public StashServerException(int status, String message) {
        super(status, message);
    }
}
