package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserExistsException;
import com.stackroute.userservice.model.UserProfile;
import com.stackroute.userservice.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    private UserProfileRepository userRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserProfile registerUser(UserProfile user) throws UserExistsException {
        final boolean existsById = this.userRepository.existsById(user.getEmail());

        if (existsById) {
            throw new UserExistsException("User already exists");
        }
        return this.userRepository.save(user);
    }

}
