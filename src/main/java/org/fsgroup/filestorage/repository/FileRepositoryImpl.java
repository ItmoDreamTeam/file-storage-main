package org.fsgroup.filestorage.repository;

import org.fsgroup.filestorage.exception.FileStorageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Repository
public class FileRepositoryImpl implements FileRepository {

    private static final Logger log = LoggerFactory.getLogger(FileRepositoryImpl.class);

    @Resource
    private StorageDirectory storageDirectory;

    @Override
    public InputStream find(String path) {
        try {
            File file = new File(storageDirectory.getDirectory(), path);
            return new FileInputStream(file);
        } catch (Exception e) {
            log.warn("Failed to retrieve file", e);
            throw new FileStorageException("Error while downloading file");
        }
    }

    @Override
    public String save(MultipartFile multipartFile) {
        String path = UUID.randomUUID().toString();
        try {
            File fileToSave = new File(storageDirectory.getDirectory(), path);
            multipartFile.transferTo(fileToSave);
        } catch (Exception e) {
            log.warn("Failed to save file", e);
            throw new FileStorageException("Error while uploading file");
        }
        return path;
    }

    @Override
    public void delete(String path) {
        try {
            File file = new File(storageDirectory.getDirectory(), path);
            if (!file.delete()) logFailedToDelete(path);
        } catch (Exception e) {
            logFailedToDelete(path);
        }
    }

    private static void logFailedToDelete(String path) {
        log.warn(String.format("Failed to delete file on path %s", path));
    }
}
