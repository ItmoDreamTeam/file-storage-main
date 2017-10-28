package org.fsgroup.filestorage.server.exception.file;

import org.fsgroup.filestorage.server.exception.FileStorageException;

public class EmptyFilenameException extends FileStorageException {

    public EmptyFilenameException() {
        super("Empty filename");
    }
}
