package org.fsgroup.filestorage.server.exception.user;

import org.fsgroup.filestorage.server.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String username) {
        super(String.format("User %s not found", username));
    }
}
