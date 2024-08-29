package org.springboot.hms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springboot.hms.models.Doctor;
import org.springboot.hms.models.Ward;

import java.util.List;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientNumber;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String firstName;

    private String address;

    private String phoneNumber;

    @OneToMany(mappedBy = "patient")
    @JsonBackReference
    private List<Hospitalization> hospitalizations;

    // Getters and Setters
}
