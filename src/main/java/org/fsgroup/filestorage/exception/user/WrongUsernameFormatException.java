package org.fsgroup.filestorage.exception.user;

import org.fsgroup.filestorage.exception.FileStorageException;

public class WrongUsernameFormatException extends FileStorageException {

    public WrongUsernameFormatException(String message) {
        super(message);
    }
}
