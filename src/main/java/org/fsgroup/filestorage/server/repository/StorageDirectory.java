package org.fsgroup.filestorage.server.repository;

import org.fsgroup.filestorage.server.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class StorageDirectory {

    private final File directory;

    public StorageDirectory(@Value("${storage.directory}") String directory) {
        this.directory = new File(directory);
    }

    public File getDirectory() {
        if (!directory.exists() && !directory.mkdir())
            //Unable to create storage directory
            throw new FileStorageException("Internal server error");
        return directory;
    }
}
