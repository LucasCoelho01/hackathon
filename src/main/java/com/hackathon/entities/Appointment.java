package com.hackathon.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackathon.entities.dtos.AppointmentRequestDto;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String initialDateTime;
    private String finalDateTime;
    private boolean isBooked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId")
    private Patient patient;

    @JsonBackReference
    public Doctor getDoctor() {
        return doctor;
    }

    @JsonBackReference
    public Patient getPatient() {
        return patient;
    }
    
    public Appointment() {}

    public Appointment(AppointmentRequestDto appointmentRequestDto, Doctor doctor) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        this.doctor = doctor;
        this.initialDateTime = convertInitialLocalDateTime(appointmentRequestDto.year(), appointmentRequestDto.month(), appointmentRequestDto.day(), appointmentRequestDto.hour(), appointmentRequestDto.minute());
        this.finalDateTime = convertFinalLocalDateTime(LocalDateTime.parse(this.initialDateTime, formatter));
    }

    public String convertInitialLocalDateTime(int year, int month, int day, int hour, int minute) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        LocalDateTime date = LocalDateTime.of(year, month, day, hour, minute);

        if (checkIfDateIsLater(date)) {
            return date.format(formatter);
        } else {
            throw new Exception("Data inválida");
        }
    }

    public String convertFinalLocalDateTime(LocalDateTime initialDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return initialDateTime.plusHours(1).format(formatter);
    }

    public boolean checkIfDateIsLater(LocalDateTime date) {
        if (date.isAfter(LocalDateTime.now())) {
            return true;
        } else {
            return false;
        }
    }
}
