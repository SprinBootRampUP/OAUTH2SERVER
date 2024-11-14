package org.authorization.oauth.repository;

import org.authorization.oauth.Entity.Role;
import org.authorization.oauth.Entity.RoleEnum;
import org.authorization.oauth.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Integer> {


    Optional<Role> findRoleByName(RoleEnum roleName);


}