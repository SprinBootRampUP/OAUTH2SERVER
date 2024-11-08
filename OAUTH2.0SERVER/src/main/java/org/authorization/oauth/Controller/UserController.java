package org.authorization.oauth.Controller;

import org.authorization.oauth.Entity.User;
import org.authorization.oauth.repository.UserRepository;
import org.authorization.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
public class UserController {

@Autowired
private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody() User user){
        userService.register(user);
        Map<String,String> response = new HashMap<>();
        response.put("message" , "User registered");
        response.put("StatusCode" , "200");
        return  ResponseEntity.ok( response );


    }

}
