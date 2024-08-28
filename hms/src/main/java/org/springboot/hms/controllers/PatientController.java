package org.springboot.hms.controllers;


import org.springboot.hms.models.Patient;
import org.springboot.hms.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        return patientService.getPatientById(id)
                .map(patient -> {
                    patient.setPatientNumber(patientDetails.getPatientNumber());
                    patient.setSurname(patientDetails.getSurname());
                    patient.setFirstName(patientDetails.getFirstName());
                    patient.setAddress(patientDetails.getAddress());
                    patient.setPhoneNumber(patientDetails.getPhoneNumber());
//                    patient.setCurrentWard(patientDetails.getCurrentWard());
//                    patient.setCurrentBedNumber(patientDetails.getCurrentBedNumber());
//                    patient.setDiagnosis(patientDetails.getDiagnosis());
//                    patient.setAttendingDoctor(patientDetails.getAttendingDoctor());
                    return ResponseEntity.ok(patientService.savePatient(patient));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(patient -> {
                    patientService.deletePatient(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}