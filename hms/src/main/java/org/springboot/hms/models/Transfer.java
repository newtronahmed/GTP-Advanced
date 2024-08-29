package org.springboot.hms.models;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hospitalization_id", nullable = false)
    private Hospitalization hospitalization;

    @ManyToOne
    @JoinColumn(name = "from_ward_id", nullable = false)
    private Ward fromWard;

    @ManyToOne
    @JoinColumn(name = "to_ward_id", nullable = false)
    private Ward toWard;

    @Column(nullable = false)
    private int fromBedNumber;

    @Column(nullable = false)
    private int toBedNumber;

    @Column(nullable = false)
    private LocalDateTime transferDateTime;
}