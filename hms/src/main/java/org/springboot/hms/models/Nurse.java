package org.springboot.hms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springboot.hms.models.Department;
import org.springboot.hms.models.Employee;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Nurse extends Employee {

    private String rotation;

    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department assignedDepartment;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // Getters and Setters
}
