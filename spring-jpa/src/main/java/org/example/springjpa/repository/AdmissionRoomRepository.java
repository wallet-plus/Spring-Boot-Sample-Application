package org.example.springjpa.repository;

import org.example.springjpa.model.AdmissionRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdmissionRoomRepository extends JpaRepository<AdmissionRoom, Long> {

    public Optional<AdmissionRoom> findByAdmissionId(Long admissionId);
}
