package org.authorization.oauth.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
   // @Order(2)
    public SecurityFilterChain appSecurityFilterChain(HttpSecurity httpSecurity)throws  Exception{

        httpSecurity
                .authorizeHttpRequests( request -> request
                        .anyRequest().authenticated()
                )
                .formLogin( Customizer.withDefaults());

        return  httpSecurity.build();
    }

}
