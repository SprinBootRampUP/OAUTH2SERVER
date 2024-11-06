package org.authorization.oauth.service;

import org.authorization.oauth.Entity.Role;
import org.authorization.oauth.Entity.RoleEnum;
import org.authorization.oauth.Entity.User;
import org.authorization.oauth.repository.RoleRepository;
import org.authorization.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(User user){

        List<Role> roles = new ArrayList<>();

        for(String roleName :user.getRoleNames()){
            RoleEnum roleEnum = RoleEnum.valueOf(roleName);
           Role role =  roleRepository.findRoleByName(roleEnum).orElseThrow( () ->
                new IllegalArgumentException( "Role " + roleName + " not found" )
            );

           roles.add(role);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())  );
        user.setRoles(roles);
        userRepository.save(user);
    }

}
