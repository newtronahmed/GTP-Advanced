package org.springboot.restapi.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AuthProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provider; // GOOGLE, GITHUB, etc.
    private String providerId; // Unique ID from the provider (e.g., Google ID)
    private String email;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public AuthProvider(User user, String provider, String providerId, String email) {
        this.user = user;
        this.provider = provider;
        this.providerId = providerId;
        this.email = email;
    }
}