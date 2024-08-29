package org.springboot.hms.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"department_id", "number"})})
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private int numberOfBeds;

    @OneToOne
    @JoinColumn(name = "supervisor_id", unique = true)
    private Nurse supervisor;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "ward")
    @JsonBackReference
    private List<Hospitalization> hospitalizations;
}
