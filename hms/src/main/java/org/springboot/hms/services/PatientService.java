package org.springboot.hms.services;

import org.springboot.hms.dto.PatientDTO;
import org.springboot.hms.models.Patient;
import org.springboot.hms.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Cacheable("patients")
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "patients", key = "#id")
    public Optional<PatientDTO> getPatientById(String id) {
        return patientRepository.findById(id).map(this::convertToDTO);
    }

    private String generatePatientNumber() {
        return "P" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Create a new patient from PatientDTO and generate an employee number
    @CacheEvict(value = "patients", allEntries = true)
    public Patient createPatient(PatientDTO patientDTO) {
        Patient patient = new Patient();
        patient.setFirstName(patientDTO.getFirstName());
        patient.setSurname(patientDTO.getSurname());
        patient.setAddress(patientDTO.getAddress());
        patient.setPhoneNumber(patientDTO.getPhoneNumber());
        patient.setPatientNumber(generatePatientNumber());
        return patientRepository.save(patient);
    }

    @CacheEvict(value = "patients", allEntries = true)
    public Optional<PatientDTO> updatePatient(String id, PatientDTO patientDTO) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setSurname(patientDTO.getSurname());
                    patient.setFirstName(patientDTO.getFirstName());
                    patient.setAddress(patientDTO.getAddress());
                    patient.setPhoneNumber(patientDTO.getPhoneNumber());
                    return convertToDTO(patientRepository.save(patient));
                });
    }
    @Cacheable(value = "patients", key = "#patientNumber")
    public Optional<PatientDTO> getPatientByPatientNumber(String patientNumber) {
        return Optional.ofNullable(patientRepository.findByPatientNumber(patientNumber))
                .map(this::convertToDTO);
    }

    @CacheEvict(value = "patients", allEntries = true)
    public boolean deletePatient(String id) {
        if (patientRepository.existsById(id)) {
            patientRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private PatientDTO convertToDTO(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setPatientNumber(patient.getPatientNumber());
        dto.setSurname(patient.getSurname());
        dto.setFirstName(patient.getFirstName());
        dto.setAddress(patient.getAddress());
        dto.setPhoneNumber(patient.getPhoneNumber());
        return dto;
    }

    private Patient convertToEntity(PatientDTO dto) {
        Patient patient = new Patient();
        patient.setId(dto.getId());
        patient.setPatientNumber(dto.getPatientNumber());
        patient.setSurname(dto.getSurname());
        patient.setFirstName(dto.getFirstName());
        patient.setAddress(dto.getAddress());
        patient.setPhoneNumber(dto.getPhoneNumber());
        return patient;
    }
}