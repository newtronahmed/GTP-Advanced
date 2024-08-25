package org.springboot.restapi.repository;

import org.springboot.restapi.models.AuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthProviderRepository extends JpaRepository<AuthProvider, Long> {
    Optional<AuthProvider> findByProviderIdAndProvider(String providerId, String provider);
}