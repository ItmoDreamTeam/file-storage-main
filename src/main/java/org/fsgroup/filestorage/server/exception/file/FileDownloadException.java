package org.fsgroup.filestorage.server.exception.file;

import org.fsgroup.filestorage.server.exception.FileStorageException;

public class FileDownloadException extends FileStorageException {

    public FileDownloadException() {
        super("An error occurred during file download");
    }
}
