package org.example.springjpa.service;

import org.example.springjpa.model.Medicine;
import org.example.springjpa.model.PatientMedicine;
import org.example.springjpa.repository.MedicineRepository;
import org.example.springjpa.repository.PatientMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PatientMedicineRepository patientMedicineRepository;

    // Add a new medicine
    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    // Get all medicines
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
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

