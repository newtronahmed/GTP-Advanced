package org.springboot.hms.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springboot.hms.models.Department;
import org.springboot.hms.models.Patient;

import java.util.List;

@Data
@Entity
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private int numberOfBeds;

    @ManyToOne
    @JoinColumn(name = "supervisor_id")
    private Nurse supervisor;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "currentWard", cascade = CascadeType.ALL)
    private List<Patient> patients;

    // Getters and Setters
}
