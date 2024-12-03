package org.example.springjpa.service;

import org.example.springjpa.model.Admission;
import org.example.springjpa.model.AdmissionRoom;  // Ensure this is imported
import org.example.springjpa.repository.AdmissionRepository;
import org.example.springjpa.repository.AdmissionRoomRepository;  // Ensure this is imported
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdmissionService {

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private AdmissionRoomRepository admissionRoomRepository;

    // Create new admission
    public Admission createAdmission(Admission admission) {
        // No default status is set; all fields, including status, should come from the API request
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

    // Update admission details, including status
    public Admission updateAdmission(Long id, Admission updatedAdmission) {
        Admission admission = getAdmissionById(id);
        admission.setStatus(updatedAdmission.getStatus());
        admission.setDischargeSummary(updatedAdmission.getDischargeSummary());
        admission.setAdmissionDate(updatedAdmission.getAdmissionDate());
        admission.setDischargeDate(updatedAdmission.getDischargeDate());
        return admissionRepository.save(admission);
    }

    // Custom method to get rooms for admissionId
    public List<AdmissionRoom> getAdmissionRooms(Long admissionId) {
        return admissionRoomRepository.findByAdmissionId(admissionId);
    }
}
