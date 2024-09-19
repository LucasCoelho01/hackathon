package com.hackathon.controllers;

import com.hackathon.entities.Patient;
import com.hackathon.entities.dtos.PatientRequestDto;
import com.hackathon.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    ResponseEntity<Patient> createPatient(@RequestBody PatientRequestDto patientRequestDto) throws Exception {
        return new ResponseEntity<>(patientService.createPatient(patientRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Patient>> listPatients() {
        return new ResponseEntity<>(patientService.listPatients(), HttpStatus.OK);
    }
}
