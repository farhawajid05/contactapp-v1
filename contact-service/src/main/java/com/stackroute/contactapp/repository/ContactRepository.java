package com.stackroute.contactapp.repository;

import com.stackroute.contactapp.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact, String> {

    Optional<Contact> findByEmail(String email);
//    List<Contact> findByNameIgnoreCase(String name);


}
