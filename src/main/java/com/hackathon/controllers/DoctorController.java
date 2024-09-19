package com.hackathon.controllers;

import com.hackathon.entities.Doctor;
import com.hackathon.entities.Patient;
import com.hackathon.entities.dtos.DoctorRequestDto;
import com.hackathon.entities.dtos.PatientRequestDto;
import com.hackathon.services.DoctorService;
import com.hackathon.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    ResponseEntity<Doctor> createDoctor(@RequestBody DoctorRequestDto doctorRequestDto) throws Exception {
        return new ResponseEntity<>(doctorService.createDoctor(doctorRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Doctor>> listDoctors() {
        return new ResponseEntity<>(doctorService.listDoctors(), HttpStatus.OK);
    }
}
