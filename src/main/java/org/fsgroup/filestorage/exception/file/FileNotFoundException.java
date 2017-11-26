package org.fsgroup.filestorage.exception.file;

import org.fsgroup.filestorage.exception.NotFoundException;

public class FileNotFoundException extends NotFoundException {

    public FileNotFoundException(int id) {
        super(String.format("File id=%d not found", id));
    }
}
