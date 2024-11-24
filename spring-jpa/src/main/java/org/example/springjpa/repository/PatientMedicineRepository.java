package org.example.springjpa.repository;

import org.example.springjpa.model.PatientMedicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientMedicineRepository extends JpaRepository<PatientMedicine, Long> {
    List<PatientMedicine> findByPatientId(Long patientId);
}

