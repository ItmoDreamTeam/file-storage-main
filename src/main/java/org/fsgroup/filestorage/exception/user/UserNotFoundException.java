package org.fsgroup.filestorage.exception.user;

import org.fsgroup.filestorage.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String username) {
        super(String.format("User %s not found", username));
    }
}
