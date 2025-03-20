package com.stackroute.userservice.service;


import com.stackroute.userservice.model.UserCredential;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("user-auth-service")
public interface UserAuthClient {

    @PostMapping("/api/v1/auth")
    boolean addCredentials(@RequestBody UserCredential credential);
}
