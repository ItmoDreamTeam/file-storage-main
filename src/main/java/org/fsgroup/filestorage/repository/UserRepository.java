package org.fsgroup.filestorage.repository;

import org.fsgroup.filestorage.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

    default boolean exists(String username) {
        return findByUsername(username) != null;
    }
}
