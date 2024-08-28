package org.springboot.hms.models;

import jakarta.persistence.*;
import org.springboot.hms.models.Department;
import org.springboot.hms.models.Employee;

@Entity
public class Nurse extends Employee {

    private String rotation;

    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department assignedDepartment;

    // Getters and Setters
}
