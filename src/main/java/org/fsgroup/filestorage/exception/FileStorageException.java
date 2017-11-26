package org.fsgroup.filestorage.exception;

import org.springframework.http.HttpStatus;

public abstract class FileStorageException extends RuntimeException {

    private final ErrorMessage errorMessage;

    public FileStorageException(String message) {
        this(HttpStatus.BAD_REQUEST, message);
    }

    public FileStorageException(HttpStatus httpStatus, String message) {
        super(message);
        errorMessage = new ErrorMessage(httpStatus, message);
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }
}
