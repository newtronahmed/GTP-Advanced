package org.springboot.restapi.controllers;


import org.springboot.restapi.models.User;
import org.springboot.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Seed database with initial data
        if (userRepository.count() == 0) {
            userRepository.save(new User("admin", "adminpassword", "admin@example.com"));
            userRepository.save(new User("user1", "password1", "user1@example.com"));
            userRepository.save(new User("user2", "password2", "user2@example.com"));
        }
    }
}
