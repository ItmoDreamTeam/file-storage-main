package org.fsgroup.filestorage.server.security;

import org.fsgroup.filestorage.server.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserAuthService {

    @Resource
    private UserService userService;

    public boolean isAuthorized(Authentication auth, String username) {
        return auth.getName().equals(username);
    }

    public boolean isAuthorized(Authentication auth, String username, int fileId) {
        return isAuthorized(auth, username) && userService.get(username).hasFile(fileId);
    }
}
