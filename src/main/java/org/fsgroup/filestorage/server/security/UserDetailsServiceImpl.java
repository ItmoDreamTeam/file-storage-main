package org.fsgroup.filestorage.server.security;

import org.fsgroup.filestorage.server.model.User;
import org.fsgroup.filestorage.server.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userRepository.exists(username))
            throw new UsernameNotFoundException(String.format("Username %s not found", username));
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(username, user.password(), Collections.emptySet());
    }
}
