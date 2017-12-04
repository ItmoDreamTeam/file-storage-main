package org.fsgroup.filestorage.service;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.fsgroup.filestorage.exception.FileStorageException;
import org.fsgroup.filestorage.exception.file.FileDownloadException;
import org.fsgroup.filestorage.exception.file.FileNotFoundException;
import org.fsgroup.filestorage.model.UploadedFile;
import org.fsgroup.filestorage.model.User;
import org.fsgroup.filestorage.repository.FileRepository;
import org.fsgroup.filestorage.repository.UploadedFileRepository;
import org.fsgroup.filestorage.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;

@Service
@Transactional
public class FileServiceImpl implements FileService {

    private static final Logger log = Logger.getLogger(FileServiceImpl.class);

    @Resource
    private UserService userService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UploadedFileRepository uploadedFileRepository;

    @Resource
    private FileRepository fileRepository;

    @Override
    public void upload(String username, MultipartFile multipartFile) {
        User user = userService.get(username);
        String path = fileRepository.save(multipartFile);
        UploadedFile file = new UploadedFile(multipartFile.getOriginalFilename(),
                formatFileSize(multipartFile.getSize()), path);
        uploadedFileRepository.save(file);
        user.addFile(file);
        userRepository.save(user);
    }

    @Override
    public void download(String username, int id, OutputStream responseStream) {
        UploadedFile file = get(username, id);
        InputStream fileStream = fileRepository.find(file.path());
        try {
            IOUtils.copy(fileStream, responseStream);
        } catch (Exception e) {
            throw new FileDownloadException();
        }
        try {
            fileStream.close();
        } catch (Exception e) {
            log.warn("Failed to close file stream", e);
        }
    }

    @Override
    public void delete(String username, int id) {
        User user = userService.get(username);
        UploadedFile file = get(username, id);
        fileRepository.delete(file.path());
        user.removeFile(file);
        userRepository.save(user);
        uploadedFileRepository.delete(file);
    }

    private UploadedFile get(String username, int id) {
        if (!uploadedFileRepository.exists(id))
            throw new FileNotFoundException(id);
        if (!userService.get(username).hasFile(id))
            throw new FileStorageException(HttpStatus.FORBIDDEN, "You are not allowed to access this file");
        return uploadedFileRepository.findOne(id);
    }

    private static String formatFileSize(long sizeInBytes) {
        if (sizeInBytes < 1000) {
            return String.format("%d B", sizeInBytes);
        } else if (sizeInBytes < 1000000) {
            return String.format("%d KB", Math.round((double) sizeInBytes / 1000));
        } else {
            return String.format("%d MB", Math.round((double) sizeInBytes / 1000000));
        }
    }
}
