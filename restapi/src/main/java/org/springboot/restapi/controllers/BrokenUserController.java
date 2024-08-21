package org.springboot.restapi.controllers;

import org.springboot.restapi.models.User;
import org.springboot.restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BrokenUserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/users")
    public List<Map<String, Object>> runQuery(@RequestParam String username) {
        try {
            // Vulnerable code: directly using user input in SQL query
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            System.out.println(query);
            return jdbcTemplate.queryForList(query);
        } catch (DataAccessException e) {
            // Proper error handling: return a generic error message
            return List.of(Map.of("error", "An error occurred while processing your request. Please try again later."));
        } catch (Exception e) {
            // Proper error handling: return a generic error message
            return List.of(Map.of("error", "An unexpected error occurred. Please try again later."));
        }
    }


    @GetMapping("/api/user-info")
    public ResponseEntity<?> getUserInfo(@RequestParam String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            return ResponseEntity.ok(userOpt.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
