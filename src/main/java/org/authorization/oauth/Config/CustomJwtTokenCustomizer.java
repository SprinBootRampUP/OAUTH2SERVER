package org.authorization.oauth.Config;

import org.authorization.oauth.Entity.CustomUserDetails;
import org.authorization.oauth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2Token;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomJwtTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void customize(JwtEncodingContext context) {

        Authentication authentication = context.getPrincipal();
        CustomUserDetails user = customUserDetailsService.loadUserByUsername(context.getPrincipal().getName());
            context.getClaims().claim("userId", user.getUserid());

            if(context.getAuthorizedScopes().contains(OidcScopes.PROFILE)){
                context.getClaims().claim("address", user.getAddress());
                context.getClaims().claim("email", user.getEmail());
                context.getClaims().claim("phone_number", user.getPhoneNumber());
                context.getClaims().claim("userId", user.getUserid());

            }


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        context.getClaims().claim("roles", authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
    }
}
