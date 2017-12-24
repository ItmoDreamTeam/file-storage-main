package org.fsgroup.filestorage.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class StorageDirectory {

    private static final Logger log = LoggerFactory.getLogger(StorageDirectory.class);

    private final File directory;

    public StorageDirectory(@Value("${storage.directory}") String directory) {
        this.directory = new File(directory);
    }

    public File getDirectory() throws UnavailableDirectoryException {
        if (!directory.exists() && !directory.mkdir()) {
            log.warn("Storage directory is unavailable");
            throw new UnavailableDirectoryException();
        }
        return directory;
    }

    private static class UnavailableDirectoryException extends Exception {
    }
}
