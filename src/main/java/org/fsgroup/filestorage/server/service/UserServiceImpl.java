package org.fsgroup.filestorage.server.service;

import org.fsgroup.filestorage.server.exception.user.UserNotFoundException;
import org.fsgroup.filestorage.server.exception.user.UsernameOccupiedException;
import org.fsgroup.filestorage.server.model.UploadedFile;
import org.fsgroup.filestorage.server.model.User;
import org.fsgroup.filestorage.server.repository.FileRepository;
import org.fsgroup.filestorage.server.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private PropertyValidator propertyValidator;

    @Resource
    private UserRepository userRepository;

    @Resource
    private FileRepository fileRepository;

    @Override
    public User get(String username) {
        if (!userRepository.exists(username))
            throw new UserNotFoundException(username);
        return userRepository.findByUsername(username);
    }

    @Override
    public void signUp(String username, String password) {
        propertyValidator.validateUsername(username);
        if (userRepository.exists(username))
            throw new UsernameOccupiedException(username);
        User user = new User(username, password);
        userRepository.save(user);
    }

    @Override
    public void edit(String username, String password) {
        User user = get(username);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public void delete(String username) {
        User user = get(username);
        user.getFiles().stream()
                .map(UploadedFile::path)
                .forEach(fileRepository::delete);
        userRepository.delete(user);
    }
}
