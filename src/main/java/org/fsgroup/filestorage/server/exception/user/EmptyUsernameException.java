package org.fsgroup.filestorage.server.exception.user;

import org.fsgroup.filestorage.server.exception.FileStorageException;

public class EmptyUsernameException extends FileStorageException {

    public EmptyUsernameException() {
        super("Empty username");
    }
}
