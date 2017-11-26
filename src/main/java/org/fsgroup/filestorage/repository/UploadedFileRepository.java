package org.fsgroup.filestorage.repository;

import org.fsgroup.filestorage.model.UploadedFile;
import org.springframework.data.repository.CrudRepository;

public interface UploadedFileRepository extends CrudRepository<UploadedFile, Integer> {
}
