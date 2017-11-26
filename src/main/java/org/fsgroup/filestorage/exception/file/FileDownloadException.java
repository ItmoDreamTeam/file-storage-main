package org.fsgroup.filestorage.exception.file;

import org.fsgroup.filestorage.exception.FileStorageException;

public class FileDownloadException extends FileStorageException {

    public FileDownloadException() {
        super("An error occurred during file download");
    }
}
