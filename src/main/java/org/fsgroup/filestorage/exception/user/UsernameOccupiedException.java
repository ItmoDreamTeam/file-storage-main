package org.fsgroup.filestorage.exception.user;

import org.fsgroup.filestorage.exception.FileStorageException;
import org.springframework.http.HttpStatus;

public class UsernameOccupiedException extends FileStorageException {

    public UsernameOccupiedException(String username) {
        super(HttpStatus.CONFLICT, String.format("Username %s occupied", username));
    }
}
