package org.springboot.hms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Doctor extends Employee {

    private String speciality;
    @OneToMany(mappedBy = "doctor")
    private List<Hospitalization> hospitalizations;
    @Id
    private Long id;

    

    // Getters and Setters
}
