package com.hackathon.services;

import com.hackathon.entities.Patient;
import com.hackathon.entities.dtos.PatientRequestDto;
import com.hackathon.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Patient createPatient(PatientRequestDto patientRequestDto) {
        Patient patient = new Patient(patientRequestDto);

        userService.createUser(patient.getEmail(), patient.getPassword());
        return patientRepository.save(patient);
    }

    public List<Patient> listPatients() {
        return patientRepository.findAll();
    }

    public Patient findByCpf(String cpf) {
        return patientRepository.findByCpf(cpf);
    }
}
