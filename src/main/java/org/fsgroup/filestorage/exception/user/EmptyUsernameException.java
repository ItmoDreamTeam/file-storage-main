package org.fsgroup.filestorage.exception.user;

import org.fsgroup.filestorage.exception.FileStorageException;

public class EmptyUsernameException extends FileStorageException {

    public EmptyUsernameException() {
        super("Empty username");
    }
}
