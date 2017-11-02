package org.fsgroup.filestorage.server.exception.user;

import org.fsgroup.filestorage.server.exception.FileStorageException;

public class WrongUsernameFormatException extends FileStorageException {

    public WrongUsernameFormatException(String message) {
        super(message);
    }
}
