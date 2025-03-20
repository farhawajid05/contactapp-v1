package com.stackroute.userservice.controller;

import com.stackroute.userservice.exception.UserExistsException;
import com.stackroute.userservice.model.UserCredential;
import com.stackroute.userservice.model.UserDTO;
import com.stackroute.userservice.model.UserProfile;
import com.stackroute.userservice.service.UserAuthClient;
import com.stackroute.userservice.service.UserProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserProfileController {

    private UserProfileService userService;
    private ModelMapper mapper;
    private UserAuthClient authClient;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public UserProfileController(UserProfileService userService, ModelMapper mapper, UserAuthClient authClient) {
        this.userService = userService;
        this.mapper = mapper;
        this.authClient = authClient;
    }

    @PostMapping("register")
    public UserProfile registerUser(@RequestBody UserDTO user) throws UserExistsException {
        final UserProfile userProfile = mapper.map(user, UserProfile.class);
        final boolean added = this.authClient.addCredentials(mapper.map(user, UserCredential.class));
        return this.userService.registerUser(userProfile);
    }

    @PostMapping("registerasync")
    public UserProfile registerUserasync(@RequestBody UserDTO user) throws UserExistsException {
        final UserProfile userProfile = mapper.map(user, UserProfile.class);
            this.rabbitTemplate.convertAndSend("user_auth_queue", mapper.map(user, UserCredential.class));
        return this.userService.registerUser(userProfile);
    }
}