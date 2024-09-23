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
    ResponseEntity<String> createAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto) throws Exception {
        appointmentService.createAppointment(appointmentRequestDto);

        String response = "Horário cadastrado com sucesso";
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Appointment>> listAppointments() {
        return new ResponseEntity<>(appointmentService.listAppointments(), HttpStatus.OK);
    }

    @PutMapping()
    ResponseEntity<String> setPatientToAppointment(@RequestBody AppointmentSetPatientRequestDto appointmentSetPatientRequestDto) throws Exception {
        Optional<Appointment> appointment = appointmentService.setPatientToAppointment(appointmentSetPatientRequestDto);

        String response = "Agendamento realizado com sucesso";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
