package org.springboot.hms.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Nurse extends Employee {

    private String rotation;

    private double salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department assignedDepartment;

    @OneToOne( fetch = FetchType.LAZY)
    private Ward supervisedWard;
}