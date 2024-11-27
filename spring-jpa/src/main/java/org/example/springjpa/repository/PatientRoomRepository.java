package org.example.springjpa.repository;

import org.example.springjpa.model.PatientRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PatientRoomRepository extends JpaRepository<PatientRoom, Long> {
    Optional<PatientRoom> findByPatientIdAndMoveDateIsNull(Long patientId);
}
