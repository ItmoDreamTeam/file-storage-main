package org.fsgroup.filestorage.server.service;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.fsgroup.filestorage.server.exception.file.FileDownloadException;
import org.fsgroup.filestorage.server.exception.file.FileNotFoundException;
import org.fsgroup.filestorage.server.model.UploadedFile;
import org.fsgroup.filestorage.server.model.User;
import org.fsgroup.filestorage.server.repository.FileRepository;
import org.fsgroup.filestorage.server.repository.UploadedFileRepository;
import org.fsgroup.filestorage.server.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.io.OutputStream;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private PropertyValidator propertyValidator;

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
        UploadedFile file = new UploadedFile(multipartFile.getOriginalFilename(), path);
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
            fileStream.close();
        } catch (Exception e) {
            throw new FileDownloadException();
        }
    }

    @Override
    public void edit(int fileId, String name) {
        propertyValidator.validateFilename(name);
        UploadedFile file = get(fileId);
        file.setName(name);
        uploadedFileRepository.save(file);
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
}
