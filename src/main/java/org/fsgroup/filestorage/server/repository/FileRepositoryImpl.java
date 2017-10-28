package org.fsgroup.filestorage.server.repository;

import org.fsgroup.filestorage.server.exception.file.FileDeleteException;
import org.fsgroup.filestorage.server.exception.file.FileDownloadException;
import org.fsgroup.filestorage.server.exception.file.FileUploadException;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Repository
public class FileRepositoryImpl implements FileRepository {

    @Resource
    private StorageDirectory storageDirectory;

    @Override
    public InputStream find(String path) {
        File file = new File(storageDirectory.getDirectory(), path);
        try {
            return new FileInputStream(file);
        } catch (Exception e) {
            throw new FileDownloadException();
        }
    }

    @Override
    public String save(MultipartFile multipartFile) {
        String path = "" + System.currentTimeMillis();
        File fileToSave = new File(storageDirectory.getDirectory(), path);
        try {
            multipartFile.transferTo(fileToSave);
        } catch (Exception e) {
            throw new FileUploadException();
        }
        return path;
    }

    @Override
    public void delete(String path) {
        File file = new File(storageDirectory.getDirectory(), path);
        if (!file.delete())
            throw new FileDeleteException();
    }
}
