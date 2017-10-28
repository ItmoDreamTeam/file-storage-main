package org.fsgroup.filestorage.server.repository;

import org.fsgroup.filestorage.server.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    default boolean exists(String username) {
        return findByUsername(username) != null;
    }
}
