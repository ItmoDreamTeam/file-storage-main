package org.fsgroup.filestorage.server.service;

import org.fsgroup.filestorage.server.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User get(String username);

    void signUp(String username, String password);

    void edit(String username, String password);

    void delete(String username);
}
