package org.springboot.hms.repositories;


import org.springboot.hms.models.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalizationRepository extends JpaRepository<Hospitalization, Long> {
    List<Hospitalization> findByPatientId(Long patientId);
    List<Hospitalization> findByWardId(Long wardId);
    List<Hospitalization> findByDoctorId(Long doctorId);
}