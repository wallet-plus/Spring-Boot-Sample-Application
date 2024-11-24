package org.example.springjpa.repository;

import org.example.springjpa.model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {
}

