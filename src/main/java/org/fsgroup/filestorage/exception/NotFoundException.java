package org.fsgroup.filestorage.exception;

import org.springframework.http.HttpStatus;

public abstract class NotFoundException extends FileStorageException {

    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
