package org.springboot.hms.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Patient {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  String id;

    @Column(nullable = false)
    private String patientNumber;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String firstName;

    private String address;

    private String phoneNumber;

    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Hospitalization> hospitalizations;

    // Getters and Setters
}