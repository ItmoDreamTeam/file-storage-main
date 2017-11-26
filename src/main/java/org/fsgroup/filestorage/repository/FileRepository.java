package org.fsgroup.filestorage.repository;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Repository
public interface FileRepository {

    InputStream find(String path);

    String save(MultipartFile multipartFile);

    void delete(String path);
}
