package com.hackathon.controllers;

import com.hackathon.entities.Appointment;
import com.hackathon.entities.Doctor;
import com.hackathon.entities.Patient;
import com.hackathon.entities.dtos.DoctorRequestDto;
import com.hackathon.entities.dtos.GetDoctorByCrmRequestDto;
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

    @GetMapping("/get-doctor-by-crm")
    ResponseEntity<Doctor> getDoctorByCrm(@RequestBody GetDoctorByCrmRequestDto doctorCrm) {
        return new ResponseEntity<>(doctorService.findByCrm(doctorCrm.crm()), HttpStatus.OK);
    }

    @GetMapping("/get-doctors-free-appointments-by-crm")
    ResponseEntity<List<Appointment>> getDoctorsFreeAppointments(@RequestBody GetDoctorByCrmRequestDto doctorCrm) {
        return new ResponseEntity<>(doctorService.getDoctorsFreeAppointments(doctorCrm.crm()), HttpStatus.OK);
    }
}
