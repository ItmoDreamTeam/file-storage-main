package org.fsgroup.filestorage.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;

@Service
public interface FileService {

    void upload(String username, MultipartFile multipartFile);

    void download(int fileId, OutputStream responseStream);

    void delete(String username, int fileId);
}
