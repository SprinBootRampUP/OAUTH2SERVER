package org.authorization.oauth.repository;

import org.authorization.oauth.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

     @Query("SELECT u FROM User u WHERE u.userName = :userName")
     User findUserByUserName(@Param("userName") String userName);


}


