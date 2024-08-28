package org.springboot.hms.controllers;

import org.springboot.hms.models.Hospitalization;
import org.springboot.hms.services.HospitalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitalizations")
public class HospitalizationController {

    @Autowired
    private HospitalizationService hospitalizationService;

    @GetMapping("/{id}")
    public ResponseEntity<Hospitalization> getHospitalizationById(@PathVariable Long id) {
        Hospitalization hospitalization = hospitalizationService.getHospitalizationById(id);
        return hospitalization != null ? ResponseEntity.ok(hospitalization) : ResponseEntity.notFound().build();
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Hospitalization>> getHospitalizationsByPatientId(@PathVariable Long patientId) {
        List<Hospitalization> hospitalizations = hospitalizationService.getHospitalizationsByPatientId(patientId);
        return ResponseEntity.ok(hospitalizations);
    }

    @PostMapping("/admit")
    public ResponseEntity<Hospitalization> admitPatient(
            @RequestParam Long patientId,
            @RequestParam Long wardId,
            @RequestParam int bedNumber,
            @RequestParam String diagnosis,
            @RequestParam Long doctorId) {
        Hospitalization hospitalization = hospitalizationService.admitPatient(patientId, wardId, bedNumber, diagnosis, doctorId);
        return ResponseEntity.ok(hospitalization);
    }

    @PutMapping("/{id}/discharge")
    public ResponseEntity<Hospitalization> dischargePatient(@PathVariable Long id) {
        Hospitalization hospitalization = hospitalizationService.dischargePatient(id);
        return ResponseEntity.ok(hospitalization);
    }

    @PutMapping("/{id}/transfer")
    public ResponseEntity<Hospitalization> transferPatient(
            @PathVariable Long id,
            @RequestParam Long newWardId,
            @RequestParam int newBedNumber) {
        Hospitalization hospitalization = hospitalizationService.transferPatient(id, newWardId, newBedNumber);
        return ResponseEntity.ok(hospitalization);
    }
}