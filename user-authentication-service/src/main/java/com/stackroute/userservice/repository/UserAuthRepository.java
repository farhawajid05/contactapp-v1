package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserCredential, String> {
    Optional<UserCredential> findByEmailAndPassword(String email, String password);
}
