package org.example.springjpa.repository;

import org.example.springjpa.model.RoomPatientAssociation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoomPatientAssociationRepository extends JpaRepository<RoomPatientAssociation, Long> {
    Optional<RoomPatientAssociation> findByPatientIdAndMoveDateIsNull(Long patientId);
}
