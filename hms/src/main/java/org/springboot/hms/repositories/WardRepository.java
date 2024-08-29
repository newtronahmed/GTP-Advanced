package org.springboot.hms.repositories;


import org.springboot.hms.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Long> {
    // In WardRepository
    @Query("SELECT w FROM Ward w WHERE w.department.id = :departmentId AND w.numberOfBeds > (SELECT COUNT(h) FROM Hospitalization h WHERE h.ward = w)")
    List<Ward> findWardsWithAvailableBedsByDepartmentId(@Param("departmentId") Long departmentId);
}

