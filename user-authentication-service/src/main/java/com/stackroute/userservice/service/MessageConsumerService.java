package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserExistsException;
import com.stackroute.userservice.model.UserCredential;
import com.stackroute.userservice.repository.UserAuthRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumerService {
    @Autowired
    private UserAuthRepository userAuthRepository;

    @RabbitListener(queues = "user_auth_queue")
    public void getUserCredential(UserCredential credential){
        final boolean existsById = this.userAuthRepository.existsById(credential.getEmail());
        if (existsById) {
            System.out.println("User already exists");
        }
        this.userAuthRepository.save(credential);
    }

}
