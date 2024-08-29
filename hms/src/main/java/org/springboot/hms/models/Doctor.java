package org.springboot.hms.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<Hospitalization> hospitalizations;

    @OneToOne(mappedBy = "director")
    private Department directorDepartment;




    // Getters and Setters
}
