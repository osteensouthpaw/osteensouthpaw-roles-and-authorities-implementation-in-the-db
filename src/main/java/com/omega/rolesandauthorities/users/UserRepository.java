package com.omega.rolesandauthorities.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
        @Query(
        """
            SELECT user
            FROM User user
            WHERE user.email = :username
        """
        )
    Optional<User> findUserByEmail(String username);
}
