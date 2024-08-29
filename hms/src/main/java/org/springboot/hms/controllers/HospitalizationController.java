package org.springboot.hms.controllers;

import jakarta.validation.Valid;
import org.springboot.hms.models.Hospitalization;
import org.springboot.hms.requests.AdmitPatientRequest;
import org.springboot.hms.requests.TransferPatientRequest;
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
    public ResponseEntity<Hospitalization> admitPatient(@Valid @RequestBody AdmitPatientRequest admitPatientRequest) {
        Hospitalization hospitalization = hospitalizationService.admitPatient(
                admitPatientRequest.getPatientId(),
                admitPatientRequest.getWardId(),
                admitPatientRequest.getBedNumber(),
                admitPatientRequest.getDiagnosis(),
                admitPatientRequest.getDoctorId()
        );
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
            @RequestBody TransferPatientRequest transferPatientRequest) {
        Hospitalization hospitalization = hospitalizationService.transferPatient(
                id,
                transferPatientRequest.getNewWardId(),
                transferPatientRequest.getNewBedNumber()
        );
        return ResponseEntity.ok(hospitalization);
    }
}
