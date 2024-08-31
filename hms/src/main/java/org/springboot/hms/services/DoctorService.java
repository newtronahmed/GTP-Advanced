package org.springboot.hms.services;

import org.springboot.hms.dto.DoctorDTO;
import org.springboot.hms.models.Doctor;
import org.springboot.hms.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Generate a unique employee number using UUID
    private String generateEmployeeNumber() {
        return "D" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Create a new doctor from DoctorDTO and generate an employee number
    public Doctor createDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setSurname(doctorDTO.getSurname());
        doctor.setAddress(doctorDTO.getAddress());
        doctor.setPhoneNumber(doctorDTO.getPhoneNumber());
        doctor.setSpeciality(doctorDTO.getSpeciality());
        doctor.setEmployeeNumber(generateEmployeeNumber());
        return doctorRepository.save(doctor);
    }



    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
