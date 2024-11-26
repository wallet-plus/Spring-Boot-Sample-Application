package org.example.springjpa.controller;

import org.example.springjpa.model.Medicine;
import org.example.springjpa.model.PatientMedicine;
import org.example.springjpa.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    // Add a new medicine
    @PostMapping
    public Medicine addMedicine(@RequestBody Medicine medicine) {
        return medicineService.addMedicine(medicine);
    }

    // Get all medicines
    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    // Assign medicine to a patient
    @PostMapping("/assign")
    public PatientMedicine assignMedicine(@RequestBody PatientMedicine patientMedicine) {
        return medicineService.assignMedicine(patientMedicine);
    }

    // Get all medicines assigned to a patient
    @GetMapping("/patient/{patientId}")
    public List<PatientMedicine> getMedicinesByPatient(@PathVariable Long patientId) {
        return medicineService.getMedicinesByPatient(patientId);
    }
}
