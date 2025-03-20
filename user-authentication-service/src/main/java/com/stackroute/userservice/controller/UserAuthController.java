package com.stackroute.userservice.controller;

import com.stackroute.userservice.exception.IncorrectPasswordException;
import com.stackroute.userservice.exception.UserExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.UserCredential;
import com.stackroute.userservice.service.TokenGeneratorService;
import com.stackroute.userservice.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class UserAuthController {

    private UserAuthService userService;

    private TokenGeneratorService generatorService;

    @Autowired
    public UserAuthController(UserAuthService userService, TokenGeneratorService generatorService) {
        this.userService = userService;
        this.generatorService = generatorService;
    }

    @PostMapping("login")
    public Map<String, String> authenticateUser(@RequestBody UserCredential credential) throws UserNotFoundException, IncorrectPasswordException {
        final UserCredential user = this.userService.authenticateUser(credential.getEmail(), credential.getPassword());
        return this.generatorService.generateToken(user);
    }

    @PostMapping
    public boolean addUser(@RequestBody UserCredential credential) throws UserNotFoundException, IncorrectPasswordException, UserExistsException {
        final UserCredential userCredential = this.userService.addUserCredential(credential);
        if(userCredential == null){
            return false;
        }
        return true;
    }
}
