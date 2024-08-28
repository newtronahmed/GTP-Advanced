package org.springboot.hms.repositories;
// Repository

import org.springboot.hms.models.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Long> {
}