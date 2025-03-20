package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.IncorrectPasswordException;
import com.stackroute.userservice.exception.UserExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.UserCredential;
import com.stackroute.userservice.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    private UserAuthRepository userAuthRepository;

    @Autowired
    public UserAuthServiceImpl(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    @Override
    public UserCredential authenticateUser(String email, String password) throws UserNotFoundException, IncorrectPasswordException {
        final boolean existsById = this.userAuthRepository.existsById(email);
        if (!existsById) {
            throw new UserNotFoundException("User Does not exist with the given email");
        }
        final Optional<UserCredential> optionalUser = this.userAuthRepository.findByEmailAndPassword(email, password);
        if (optionalUser.isEmpty()) {
            throw new IncorrectPasswordException("Password Mismatch");
        }
        return optionalUser.get();
    }

    @Override
    public UserCredential addUserCredential(UserCredential credential) throws UserExistsException {
        final boolean existsById = this.userAuthRepository.existsById(credential.getEmail());
        if (existsById) {
            throw new UserExistsException("User exists");
        }
        return this.userAuthRepository.save(credential);
    }
}
