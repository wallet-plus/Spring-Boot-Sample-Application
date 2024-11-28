package org.example.springjpa.controller;

import org.example.springjpa.model.Admission;
import org.example.springjpa.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    // Add a new admission
    @PostMapping
    public ResponseEntity<Admission> createAdmission(@RequestBody Admission admission) {
        Admission savedAdmission = admissionService.createAdmission(admission);
        return ResponseEntity.ok(savedAdmission);
    }

    // Get all admissions
    @GetMapping
    public ResponseEntity<List<Admission>> getAllAdmissions() {
        List<Admission> admissions = admissionService.getAllAdmissions();
        return ResponseEntity.ok(admissions);
    }

    // Get an admission by ID
    @GetMapping("/{id}")
    public ResponseEntity<Admission> getAdmissionById(@PathVariable Long id) {
        Admission admission = admissionService.getAdmissionById(id);
        return ResponseEntity.ok(admission);
    }

    // Discharge a patient
    @PutMapping("/{id}/discharge")
    public ResponseEntity<Admission> dischargePatient(
            @PathVariable Long id,
            @RequestBody String dischargeSummary) {
        Admission dischargedAdmission = admissionService.dischargePatient(id, dischargeSummary);
        return ResponseEntity.ok(dischargedAdmission);
    }
}

