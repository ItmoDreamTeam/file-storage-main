package org.fsgroup.filestorage.server.repository;

import org.fsgroup.filestorage.server.model.UploadedFile;
import org.springframework.data.repository.CrudRepository;

public interface UploadedFileRepository extends CrudRepository<UploadedFile, Integer> {
}
