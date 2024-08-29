package org.springboot.hms.repositories;


import org.springboot.hms.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p JOIN p.hospitalizations h JOIN h.ward w WHERE w.number = :wardNumber AND w.department.code = :departmentCode")
    List<Patient> findPatientsByWardNumberAndDepartmentCode(@Param("wardNumber") int wardNumber, @Param("departmentCode") String departmentCode);

}
