
package org.springboot.hms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Hospitalization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonManagedReference
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "ward_id", nullable = false)
    @JsonBackReference
    private Ward ward;

    @Column(nullable = false)
    private int bedNumber;

    private String diagnosis;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonBackReference
    private Doctor doctor;

    @Column(nullable = false)
    private LocalDate admissionDate;

    private LocalDate dischargeDate;
}