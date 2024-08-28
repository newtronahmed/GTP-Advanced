package org.springboot.hms.controllers;


import org.springboot.hms.models.Nurse;
import org.springboot.hms.services.NurseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    @Autowired
    private NurseService nurseService;

    @GetMapping
    public List<Nurse> getAllNurses() {
        return nurseService.getAllNurses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Nurse> getNurseById(@PathVariable Long id) {
        return nurseService.getNurseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Nurse createNurse(@RequestBody Nurse nurse) {
        return nurseService.saveNurse(nurse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Nurse> updateNurse(@PathVariable Long id, @RequestBody Nurse nurseDetails) {
        return nurseService.getNurseById(id)
                .map(nurse -> {
                    nurse.setEmployeeNumber(nurseDetails.getEmployeeNumber());
                    nurse.setSurname(nurseDetails.getSurname());
                    nurse.setFirstName(nurseDetails.getFirstName());
                    nurse.setAddress(nurseDetails.getAddress());
                    nurse.setPhoneNumber(nurseDetails.getPhoneNumber());
                    nurse.setRotation(nurseDetails.getRotation());
                    nurse.setSalary(nurseDetails.getSalary());
                    nurse.setAssignedDepartment(nurseDetails.getAssignedDepartment());
                    return ResponseEntity.ok(nurseService.saveNurse(nurse));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNurse(@PathVariable Long id) {
        return nurseService.getNurseById(id)
                .map(nurse -> {
                    nurseService.deleteNurse(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}