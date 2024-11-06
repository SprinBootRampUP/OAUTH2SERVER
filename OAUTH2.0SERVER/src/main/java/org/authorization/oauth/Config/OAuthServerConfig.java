package org.authorization.oauth.Config;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.ietf.jgss.Oid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

@Configuration
public class OAuthServerConfig {



    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain authorizationServerSecurityFilterChain
            (HttpSecurity httpSecurity, RegisteredClientRepository registeredClientRepository) throws Exception {


        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity);



        httpSecurity.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        return httpSecurity.formLogin(Customizer.withDefaults()).build();


      //  return  httpSecurity.build();


    }




@Bean
    public RegisteredClientRepository registeredClientRepository(){

        RegisteredClient registeredClient = RegisteredClient.withId("test_id")
                .clientId("resource_server")
                .clientSecret("secret")
            //    .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
              //  .clientAuthenticationMethod(ClientAuthenticationMethod.NONE) //
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)//
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://localhost:3001/login/oauth/oauthserver")
               // .redirectUri("abc")

                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                //.scope("read")
                .clientSettings( ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();

        return  new InMemoryRegisteredClientRepository(registeredClient);
    }



@Bean
    public AuthorizationServerSettings authorizationServerSettings(){
        return AuthorizationServerSettings.builder().build();
    }




    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        RSAKey rsaKey = generateRsa();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    private static RSAKey generateRsa() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }


    @Bean
    public JwtDecoder jwtDecoder( JWKSource<SecurityContext> jwkSource){
        return  OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }


    @Bean
    UserDetailsService inMemoryUserDetailsManager(){

        User.UserBuilder userBuilder = User.builder();
        UserDetails user=userBuilder.username("user").password("user").roles("USER").build();
         UserDetails author=userBuilder.username("author").password("author").roles("AUTHOR" ,"USER").build();
        UserDetails admin=userBuilder.username("admin").password("admin").roles("ADMIN").build();

         return new InMemoryUserDetailsManager(user,author,admin);

    }


    //
        @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
