package org.authorization.oauth.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class S extends UsernamePasswordAuthenticationToken {

    private Long userid ;

    public S(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public S(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public S(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, Long userid) {
        super(principal, credentials, authorities);
        this.userid= userid;
    }

    public Long getUserid() {
        return userid;
    }
}
