package org.fsgroup.filestorage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

public interface FileService {

    void upload(String username, MultipartFile multipartFile);

    void download(String username, int id, OutputStream responseStream);

    void delete(String username, int id);
}
