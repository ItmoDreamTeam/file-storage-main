package org.fsgroup.filestorage.service;

import org.fsgroup.filestorage.exception.FileStorageException;
import org.springframework.stereotype.Component;

@Component
public class PropertyValidator {

    private static final int PASSWORD_MIN_LENGTH = 10;

    public void validateUsername(String username) {
        if (username.trim().isEmpty())
            throw new FileStorageException("Username cannot be empty");
        if (!username.matches("[A-Za-z0-9]+"))
            throw new FileStorageException("Username can only contain English letters and digits");
    }

    public void validatePassword(String password) {
        password = password == null ? "" : password.trim();
        if (password.length() < PASSWORD_MIN_LENGTH)
            throw new FileStorageException(String.format("Your password must be longer than %d symbols", PASSWORD_MIN_LENGTH));
    }
}
