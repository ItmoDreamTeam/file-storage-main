package org.fsgroup.filestorage.service;

import org.fsgroup.filestorage.model.User;

public interface UserService {

    User get(String username);

    void signUp(String username, String password);

    void edit(String username, String password);

    void delete(String username);
}
