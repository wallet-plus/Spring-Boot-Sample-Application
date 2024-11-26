package org.example.springjpa.service;

import org.example.springjpa.model.Patient;
import org.example.springjpa.model.PatientMedicine;
import org.example.springjpa.repository.PatientRepository;
import org.example.springjpa.repository.PatientMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientMedicineRepository patientMedicineRepository;

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get patient by ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Save a new or updated patient
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Delete a patient by ID
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }


    // Assign a medicine to a patient
    public PatientMedicine assignMedicineToPatient(PatientMedicine patientMedicine) {
        return patientMedicineRepository.save(patientMedicine);
    }

    // Get all medicines assigned to a specific patient
    public List<PatientMedicine> getMedicinesByPatient(Long patientId) {
        return patientMedicineRepository.findByPatientId(patientId);
    }
}
