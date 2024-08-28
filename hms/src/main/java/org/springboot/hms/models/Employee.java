package org.springboot.hms.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String employeeNumber;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String firstName;

    private String address;

    private String phoneNumber;

    // Getters and Setters
}
