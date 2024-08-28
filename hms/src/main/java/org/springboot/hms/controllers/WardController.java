package org.springboot.hms.controllers;

import org.springboot.hms.models.Ward;
import org.springboot.hms.services.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wards")
public class WardController {

    @Autowired
    private WardService wardService;

    @GetMapping
    public List<Ward> getAllWards() {
        return wardService.getAllWards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ward> getWardById(@PathVariable Long id) {
        return wardService.getWardById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Ward createWard(@RequestBody Ward ward) {
        return wardService.saveWard(ward);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ward> updateWard(@PathVariable Long id, @RequestBody Ward wardDetails) {
        return wardService.getWardById(id)
                .map(ward -> {
                    ward.setNumber(wardDetails.getNumber());
                    ward.setNumberOfBeds(wardDetails.getNumberOfBeds());
                    ward.setSupervisor(wardDetails.getSupervisor());
                    ward.setDepartment(wardDetails.getDepartment());
                    return ResponseEntity.ok(wardService.saveWard(ward));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWard(@PathVariable Long id) {
        return wardService.getWardById(id)
                .map(ward -> {
                    wardService.deleteWard(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
