package com.hackathon.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackathon.entities.dtos.PatientRequestDto;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.List;

@Data
@Entity
@Table(name = "patients")
public class Patient implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    @JsonManagedReference
    public List<Appointment> getAppointments() {
        return appointments;
    }

    public Patient(){}

    public Patient(PatientRequestDto patientRequestDto) {
        this.name = patientRequestDto.name();
        this.cpf = patientRequestDto.cpf();
        this.email = patientRequestDto.email();
        this.password = generatePassword();
    }

    private String generatePassword() {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final SecureRandom RANDOM = new SecureRandom();

        StringBuilder randomPassword = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            randomPassword.append(CHARACTERS.charAt(randomIndex));
        }

        return randomPassword.toString();
    }
}
