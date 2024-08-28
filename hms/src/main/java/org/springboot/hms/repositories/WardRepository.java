package org.springboot.hms.repositories;


import org.springboot.hms.models.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward, Long> {
}

