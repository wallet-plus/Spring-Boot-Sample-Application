package org.example.springjpa.service;

import org.example.springjpa.model.Medicine;
import org.example.springjpa.model.PatientMedicine;
import org.example.springjpa.repository.MedicineRepository;
import org.example.springjpa.repository.PatientMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PatientMedicineRepository patientMedicineRepository;

    // Get all medicines
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    // Get a medicine by ID
    public Optional<Medicine> getMedicineById(Long id) {
        return medicineRepository.findById(id);
    }

    // Add or create a new medicine
    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // Update an existing medicine
    public Medicine updateMedicine(Long id, Medicine medicineDetails) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        // Update fields
        medicine.setName(medicineDetails.getName());
        medicine.setDescription(medicineDetails.getDescription());
        medicine.setPrice(medicineDetails.getPrice());

        return medicineRepository.save(medicine);
    }

    // Delete a medicine by ID
    public void deleteMedicine(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        medicineRepository.delete(medicine);
    }

    // Assign a medicine to a patient
    public PatientMedicine assignMedicine(PatientMedicine patientMedicine) {
        return patientMedicineRepository.save(patientMedicine);
    }

    // Get all medicines assigned to a specific patient
    public List<PatientMedicine> getMedicinesByPatient(Long patientId) {
        return patientMedicineRepository.findByPatientId(patientId);
    }
}

