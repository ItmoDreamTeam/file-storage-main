package org.fsgroup.filestorage.service;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.fsgroup.filestorage.exception.file.FileDownloadException;
import org.fsgroup.filestorage.exception.file.FileNotFoundException;
import org.fsgroup.filestorage.model.UploadedFile;
import org.fsgroup.filestorage.model.User;
import org.fsgroup.filestorage.repository.FileRepository;
import org.fsgroup.filestorage.repository.UploadedFileRepository;
import org.fsgroup.filestorage.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;

@Service
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
    public void download(int fileId, OutputStream responseStream) {
        UploadedFile file = get(fileId);
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
    public void delete(String username, int fileId) {
        User user = userService.get(username);
        UploadedFile file = get(fileId);
        fileRepository.delete(file.path());
        user.removeFile(file);
        userRepository.save(user);
        uploadedFileRepository.delete(file);
    }

    private UploadedFile get(int fileId) {
        if (!uploadedFileRepository.exists(fileId))
            throw new FileNotFoundException(fileId);
        return uploadedFileRepository.findOne(fileId);
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