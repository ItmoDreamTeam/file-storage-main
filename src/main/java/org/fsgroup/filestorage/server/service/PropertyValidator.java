package org.fsgroup.filestorage.server.service;

import org.fsgroup.filestorage.server.exception.file.EmptyFilenameException;
import org.fsgroup.filestorage.server.exception.user.EmptyUsernameException;
import org.springframework.stereotype.Component;

@Component
public class PropertyValidator {

    public void validateUsername(String username) {
        if (isEmpty(username))
            throw new EmptyUsernameException();
    }

    public void validateFilename(String filename) {
        if (isEmpty(filename))
            throw new EmptyFilenameException();
    }

    private static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }
}
