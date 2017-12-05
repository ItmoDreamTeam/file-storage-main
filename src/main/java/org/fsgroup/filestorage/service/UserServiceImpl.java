package org.fsgroup.filestorage.service;

import org.fsgroup.filestorage.exception.FileStorageException;
import org.fsgroup.filestorage.model.UploadedFile;
import org.fsgroup.filestorage.model.User;
import org.fsgroup.filestorage.repository.FileRepository;
import org.fsgroup.filestorage.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private PropertyValidator propertyValidator;

    @Resource
    private UserRepository userRepository;

    @Resource
    private FileRepository fileRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public User get(String username) {
        if (!userRepository.existsByUsername(username))
            throw new FileStorageException(String.format("Username %s not found", username));
        return userRepository.findByUsername(username);
    }

    @Override
    public void signUp(String username, String password) {
        propertyValidator.validateUsername(username);
        propertyValidator.validatePassword(password);
        if (userRepository.existsByUsername(username))
            throw new FileStorageException(String.format("Username %s occupied", username));
        User user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void edit(String username, String password) {
        User user = get(username);
        propertyValidator.validatePassword(password);
        user.setPassword(passwordEncoder.encode(password));
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
