package org.springboot.hms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Doctor extends Employee {

    private String speciality;


    // Getters and Setters
}
