package org.springboot.hms.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

import java.util.List;

@Data
@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"department_id", "number"})})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private int numberOfBeds;

    @Column(nullable = false)
    private int availableBeds;

    @OneToOne
    @JoinColumn(name = "supervisor_id", unique = true)
    private Nurse supervisor;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "ward")
    private List<Hospitalization> hospitalizations;

    // Method to decrement available beds
    public void decrementAvailableBeds() {
        if (availableBeds <= 0) {
            throw new RuntimeException("No available beds in the ward");
        }
        this.availableBeds -= 1;
    }
    public void incrementAvailableBeds() {
        if (this.availableBeds < this.numberOfBeds) {
            this.availableBeds += 1;
        } else {
            throw new RuntimeException("All beds are already available");
        }
    }

}
