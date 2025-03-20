package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserExistsException;
import com.stackroute.userservice.model.UserProfile;

public interface UserProfileService {
    UserProfile registerUser(UserProfile user) throws UserExistsException;
}
