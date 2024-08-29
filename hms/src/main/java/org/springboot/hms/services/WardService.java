package org.springboot.hms.services;


import jakarta.transaction.Transactional;
import org.springboot.hms.models.Nurse;
import org.springboot.hms.models.Ward;
import org.springboot.hms.repositories.NurseRepository;
import org.springboot.hms.repositories.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WardService {

    @Autowired
    private WardRepository wardRepository;
    @Autowired
    private NurseRepository nurseRepository;

    public List<Ward> getAllWards() {
        return wardRepository.findAll();
    }

    public Optional<Ward> getWardById(Long id) {
        return wardRepository.findById(id);
    }

    public Ward saveWard(Ward ward) {
        return wardRepository.save(ward);
    }

    public void deleteWard(Long id) {
        wardRepository.deleteById(id);
    }
    @Transactional
    @CacheEvict(value = {"wards", "nurses"}, allEntries = true)
    public Ward assignSupervisorToWard(Long wardId, Long nurseId) {
        Ward ward = wardRepository.findById(wardId).orElseThrow(() -> new RuntimeException("Ward not found"));
        Nurse nurse = nurseRepository.findById(nurseId).orElseThrow(() -> new RuntimeException("Nurse not found"));

        ward.setSupervisor(nurse);
        nurse.setAssignedDepartment(ward.getDepartment());

        wardRepository.save(ward);
        nurseRepository.save(nurse);

        return ward;
    }
}