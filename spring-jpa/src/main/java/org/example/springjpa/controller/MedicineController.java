package org.example.springjpa.controller;

import org.example.springjpa.model.Medicine;
import org.example.springjpa.model.PatientMedicine;
import org.example.springjpa.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    // Get all medicines
    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    // Get a medicine by ID
    @GetMapping("/{id}")
    public Optional<Medicine> getMedicineById(@PathVariable Long id) {
        return medicineService.getMedicineById(id);
    }

    // Add a new medicine
    @PostMapping
    public Medicine createMedicine(@RequestBody Medicine medicine) {
        return medicineService.saveMedicine(medicine);
    }

    // Update an existing medicine
    @PutMapping("/{id}")
    public Medicine updateMedicine(@PathVariable Long id, @RequestBody Medicine medicineDetails) {
        Medicine medicine = medicineService.getMedicineById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        // Update fields
        medicine.setName(medicineDetails.getName());
        medicine.setDescription(medicineDetails.getDescription());
        medicine.setPrice(medicineDetails.getPrice());

        return medicineService.saveMedicine(medicine);
    }

    // Delete a medicine
    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
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
