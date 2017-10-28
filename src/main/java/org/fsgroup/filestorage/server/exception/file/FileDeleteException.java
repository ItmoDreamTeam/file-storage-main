package org.fsgroup.filestorage.server.exception.file;

import org.fsgroup.filestorage.server.exception.FileStorageException;

public class FileDeleteException extends FileStorageException {

    public FileDeleteException() {
        super("An error occurred during file deletion");
    }
}
