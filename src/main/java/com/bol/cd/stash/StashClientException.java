package com.bol.cd.stash;

public class StashClientException extends StashException {
    public StashClientException(int status, String message, Throwable cause) {
        super(status, message, cause);
    }

    public StashClientException(int status, String message) {
        super(status, message);
    }
}
