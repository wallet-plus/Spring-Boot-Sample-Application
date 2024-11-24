package org.example.springjpa.service;

import org.example.springjpa.model.Admission;
import org.example.springjpa.repository.AdmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    // Create new admission
    public Admission createAdmission(Admission admission) {
        admission.setStatus("Admitted");
        admission.setAdmissionDate(new Date());
        return admissionRepository.save(admission);
    }

    // Get all admissions
    public List<Admission> getAllAdmissions() {
        return admissionRepository.findAll();
    }

    // Get admission by ID
    public Admission getAdmissionById(Long id) {
        return admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));
    }

    // Discharge a patient
    public Admission dischargePatient(Long id, String dischargeSummary) {
        Admission admission = getAdmissionById(id);
        admission.setStatus("Discharged");
        admission.setDischargeDate(new Date());
        admission.setDischargeSummary(dischargeSummary);
        return admissionRepository.save(admission);
    }
}
