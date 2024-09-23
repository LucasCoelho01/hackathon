package com.hackathon.repositories;

import com.hackathon.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByCpf(String cpf);
}
