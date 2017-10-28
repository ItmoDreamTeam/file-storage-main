package org.fsgroup.filestorage.server.exception;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

    private final long timestamp;
    private final int status;
    private final HttpStatus error;
    private final String message;

    public ErrorMessage(HttpStatus error, String message) {
        this.timestamp = System.currentTimeMillis();
        this.status = error.value();
        this.error = error;
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public HttpStatus getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
