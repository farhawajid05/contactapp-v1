package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.IncorrectPasswordException;
import com.stackroute.userservice.exception.UserExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.UserCredential;

public interface UserAuthService {

    UserCredential authenticateUser(String email, String password) throws UserNotFoundException, IncorrectPasswordException;
    UserCredential addUserCredential(UserCredential credential) throws UserExistsException;
}
