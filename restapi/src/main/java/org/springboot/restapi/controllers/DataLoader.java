package org.springboot.restapi.controllers;


import org.springboot.restapi.models.User;
import org.springboot.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Seed database with initial data
        if (userRepository.count() == 0) {
            userRepository.save(new User("admin", passwordEncoder.encode("adminpassword"), "admin@example.com", "ROLE_ADMIN"));

            userRepository.save(new User("user1", passwordEncoder.encode("password1"), "user1@example.com", "ROLE_USER"));
        }
    }
}
