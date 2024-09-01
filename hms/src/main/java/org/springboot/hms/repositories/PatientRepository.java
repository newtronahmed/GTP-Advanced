package org.springboot.hms.repositories;


import org.springboot.hms.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends MongoRepository<Patient, String> {
    Patient findByPatientNumber(@Param("patientNumber") String patientNumber);
}
