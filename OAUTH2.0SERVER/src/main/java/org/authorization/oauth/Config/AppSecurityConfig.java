package org.authorization.oauth.Config;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration.jwtDecoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {


    @Autowired
    private JWKSource<SecurityContext> jwkSource;

    @Bean
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity httpSecurity)throws  Exception{

        httpSecurity
                .csrf(csrf -> csrf.ignoringRequestMatchers("/oauth/register"))

                .authorizeHttpRequests( request -> request
                        .requestMatchers("/oauth/register" ).permitAll()

                        .anyRequest().authenticated()
                )
                .formLogin( Customizer.withDefaults());

        return  httpSecurity.build();
    }


}
