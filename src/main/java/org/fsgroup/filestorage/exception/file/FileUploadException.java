package org.fsgroup.filestorage.exception.file;

import org.fsgroup.filestorage.exception.FileStorageException;

public class FileUploadException extends FileStorageException {

    public FileUploadException() {
        super("An error occurred during file upload");
    }
}
