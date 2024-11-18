package org.authorization.oauth.Controller;

import org.authorization.oauth.DTOS.UserDTO;
import org.authorization.oauth.Entity.User;
import org.authorization.oauth.repository.UserRepository;
import org.authorization.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth/api")
public class UserController {

@Autowired
private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody UserDTO userDTO){
        userService.register(userDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message" , "User registered");
        return  ResponseEntity.ok( response );

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/approve")
    public ResponseEntity<Map<String,String>> approveAuthor(@RequestParam Long id){

        Map<String,String> response = new HashMap<>();

        try{
            userService.addAuthorRole(id);
            response.put("message" , "Role added");
            return  ResponseEntity.ok( response );

        } catch (Exception e) {
            response.put("message" , e.getMessage());
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }

    }

}
