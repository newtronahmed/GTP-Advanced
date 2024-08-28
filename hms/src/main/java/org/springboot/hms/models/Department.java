package org.springboot.hms.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springboot.hms.models.Doctor;
import org.springboot.hms.models.Ward;

import java.util.List;
@Data
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    private String building;

    @OneToOne
    @JoinColumn(name = "director_id")
    private Doctor director;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Ward> wards;

    // Getters and Setters
}
