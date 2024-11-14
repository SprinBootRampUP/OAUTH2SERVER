package org.authorization.oauth.Entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map( role ->
                        new SimpleGrantedAuthority( role.getName().toString())
                ) .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public Long getUserid() {
        return user.getId();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    public Long getPhoneNumber() {
        return user.getPhoneNumber();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getAddress() {
        return user.getAddress();
    }


}
