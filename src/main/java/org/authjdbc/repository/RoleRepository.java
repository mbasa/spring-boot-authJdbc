package org.authjdbc.repository;

import org.authjdbc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by suman.das on 11/28/18.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
