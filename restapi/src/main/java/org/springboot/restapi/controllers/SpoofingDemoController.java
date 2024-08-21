package org.springboot.restapi.controllers;

import org.springboot.restapi.models.User;
import org.springboot.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SpoofingDemoController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/user-data")
    public String getUserData(@RequestParam String username) {
        // Vulnerable code: using user input directly to fetch data
        Optional<User> user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return "Sensitive data for " + username + ": " + user.get();
        } else {
            return "";
        }
    }
}
