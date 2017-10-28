package org.fsgroup.filestorage.server.exception.file;

import org.fsgroup.filestorage.server.exception.NotFoundException;

public class FileNotFoundException extends NotFoundException {

    public FileNotFoundException(int id) {
        super(String.format("File id=%d not found", id));
    }
}
