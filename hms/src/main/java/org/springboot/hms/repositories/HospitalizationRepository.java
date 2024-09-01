package org.springboot.hms.repositories;


import org.springboot.hms.models.Hospitalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface HospitalizationRepository extends MongoRepository<Hospitalization, Long> {
    List<Hospitalization> findByPatientId(String patient_id);
//    List<Hospitalization> findByWardId(Long wardId);
//    List<Hospitalization> findByDoctorId(Long doctorId);
}