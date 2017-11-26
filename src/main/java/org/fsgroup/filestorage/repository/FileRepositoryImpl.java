package org.fsgroup.filestorage.repository;

import org.apache.log4j.Logger;
import org.fsgroup.filestorage.exception.file.FileDownloadException;
import org.fsgroup.filestorage.exception.file.FileUploadException;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Repository
public class FileRepositoryImpl implements FileRepository {

    private static final Logger log = Logger.getLogger(FileRepositoryImpl.class);

    @Resource
    private StorageDirectory storageDirectory;

    @Override
    public InputStream find(String path) {
        try {
            File file = new File(storageDirectory.getDirectory(), path);
            return new FileInputStream(file);
        } catch (Exception e) {
            log.warn("Failed to retrieve file", e);
            throw new FileDownloadException();
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
            throw new FileUploadException();
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
