package org.fsgroup.filestorage.server.exception.user;

import org.fsgroup.filestorage.server.exception.FileStorageException;
import org.springframework.http.HttpStatus;

public class UsernameOccupiedException extends FileStorageException {

    public UsernameOccupiedException(String username) {
        super(HttpStatus.CONFLICT, String.format("Username %s occupied", username));
    }
}
