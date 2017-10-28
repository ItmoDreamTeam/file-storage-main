package org.fsgroup.filestorage.server.exception.file;

import org.fsgroup.filestorage.server.exception.FileStorageException;

public class FileUploadException extends FileStorageException {

    public FileUploadException() {
        super("An error occurred during file upload");
    }
}
