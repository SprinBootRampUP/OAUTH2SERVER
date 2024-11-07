package org.authorization.oauth.service;

import jakarta.transaction.Transactional;
import org.authorization.oauth.Entity.CustomUserDetails;
import org.authorization.oauth.Entity.Role;
import org.authorization.oauth.Entity.RoleEnum;
import org.authorization.oauth.Entity.User;
import org.authorization.oauth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    @Transactional
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(username);
//        System.out.printf("user" + user);
        return new CustomUserDetails(user);
    }

    private Collection<GrantedAuthority> getAuthorities(List<Role> roles) {

         List<GrantedAuthority> authorities = roles.stream()
                .map( role ->
                    new SimpleGrantedAuthority( role.getName().toString())
                ) .collect(Collectors.toList());

       return  authorities;
    }
}
