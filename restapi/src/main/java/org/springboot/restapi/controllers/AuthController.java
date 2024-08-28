package org.springboot.restapi.controllers;

import org.springboot.restapi.responses.AuthResponse;
import org.springboot.restapi.requests.LoginRequest;
import org.springboot.restapi.models.User;
import org.springboot.restapi.security.JwtTokenProvider;
import org.springboot.restapi.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        User user = userService.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with this username or email"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                jwt,
                tokenProvider.getJwtExpirationInMs()
        );

        return ResponseEntity.ok(authResponse);
    }
}

