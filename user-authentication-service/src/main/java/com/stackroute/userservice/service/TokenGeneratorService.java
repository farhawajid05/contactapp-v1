package com.stackroute.userservice.service;

import com.stackroute.userservice.model.UserCredential;

import java.util.Map;

public interface TokenGeneratorService {

    Map<String, String> generateToken(UserCredential user);
}
