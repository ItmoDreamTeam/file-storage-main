package org.fsgroup.filestorage.exception;

import org.springframework.http.HttpStatus;

public class FileStorageException extends RuntimeException {

    private final ErrorResponse errorResponse;

    public FileStorageException(String message) {
        this(HttpStatus.BAD_REQUEST, message);
    }

    public FileStorageException(HttpStatus httpStatus, String message) {
        super(message);
        errorResponse = new ErrorResponse(httpStatus, message, message);
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }
}
