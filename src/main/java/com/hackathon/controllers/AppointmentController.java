package com.hackathon.controllers;

import com.hackathon.entities.Appointment;
import com.hackathon.entities.Doctor;
import com.hackathon.entities.dtos.AppointmentRequestDto;
import com.hackathon.entities.dtos.AppointmentSetPatientRequestDto;
import com.hackathon.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) throws Exception {
        return new ResponseEntity<>(appointmentService.createAppointment(appointmentRequestDto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Appointment>> listAppointments() {
        return new ResponseEntity<>(appointmentService.listAppointments(), HttpStatus.OK);
    }

    @PutMapping()
    ResponseEntity<Optional<Appointment>> setPatientToAppointment(@RequestBody AppointmentSetPatientRequestDto appointmentSetPatientRequestDto) throws Exception {
        Optional<Appointment> appointment = appointmentService.setPatientToAppointment(appointmentSetPatientRequestDto);

        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}
