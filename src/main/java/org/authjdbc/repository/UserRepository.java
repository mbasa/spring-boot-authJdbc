package org.authjdbc.repository;

import org.authjdbc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by suman.das on 11/28/18.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
