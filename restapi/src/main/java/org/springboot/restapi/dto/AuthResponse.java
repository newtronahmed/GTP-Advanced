package org.springboot.restapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    private Long id;
    private final String username;
    private String email;
    private String token;
    private long expirationTime;

    public AuthResponse(Long id, String email,String username, String token, long expirationTime) {
        this.id = id;
        this.email = email;
        this.username=username;
        this.token = token;
        this.expirationTime = expirationTime;
    }

}