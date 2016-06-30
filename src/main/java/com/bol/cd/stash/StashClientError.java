package com.bol.cd.stash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * <pre>
 * {
 * "errors": [
 * {
 * "context": "name",
 * "message": "The name should be between 1 and 255 characters.",
 * "exceptionName": null
 * },
 * {
 * "context": "email",
 * "message": "The email should be a valid email address.",
 * "exceptionName": null
 * }
 * ]
 * } * </pre>
 */
public class StashClientError extends RuntimeException {

    private List<Error> errors = new ArrayList<>();

    @Override
    public String getMessage() {
        return Arrays.toString(errors.toArray());
    }

    public List<Error> getErrors() {
        return errors;
    }

    static class Error {
        private String context;
        private String message;
        private String exceptionName;

        public Error() {
        }

        public Error(String context, String message, String exceptionName) {
            this.context = context;
            this.message = message;
            this.exceptionName = exceptionName;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getExceptionName() {
            return exceptionName;
        }

        public void setExceptionName(String exceptionName) {
            this.exceptionName = exceptionName;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "context='" + context + '\'' +
                    ", message='" + message + '\'' +
                    ", exceptionName='" + exceptionName + '\'' +
                    '}';
        }
    }
}
