package org.springboot.hms.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Doctor extends Employee {

    private String speciality;

    @OneToMany(mappedBy = "doctor")
    @JsonIgnore
    private List<Hospitalization> hospitalizations;

    @OneToOne(mappedBy = "director")
    @JsonIgnore
    private Department directorDepartment;

    // Getters and Setters
}