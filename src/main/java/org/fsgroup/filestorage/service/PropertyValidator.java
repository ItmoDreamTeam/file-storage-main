package org.fsgroup.filestorage.service;

import org.fsgroup.filestorage.exception.user.EmptyUsernameException;
import org.fsgroup.filestorage.exception.user.WrongUsernameFormatException;
import org.springframework.stereotype.Component;

@Component
public class PropertyValidator {

    public void validateUsername(String username) {
        if (username.trim().isEmpty())
            throw new EmptyUsernameException();
        if (!username.matches("[A-Za-z0-9]+"))
            throw new WrongUsernameFormatException("Username can only contain English letters and digits");
    }
}
