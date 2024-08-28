package org.springboot.hms.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springboot.hms.models.Doctor;
import org.springboot.hms.models.Ward;

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

    @ManyToOne
    @JoinColumn(name = "ward_id")
    private Ward currentWard;

    private int currentBedNumber;

    private String diagnosis;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor attendingDoctor;

    // Getters and Setters
}
