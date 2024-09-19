package com.hackathon.entities;

import com.hackathon.entities.dtos.PatientRequestDto;
import jakarta.persistence.*;
import lombok.Data;

import java.security.SecureRandom;

@Data
@Entity
@Table(name = "patients")
public class Patient {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String cpf;
    private String email;
    private String password;

    public Patient(){}

    public Patient(PatientRequestDto patientRequestDto) {
        this.name = patientRequestDto.name();
        this.cpf = patientRequestDto.cpf();
        this.email = patientRequestDto.email();
        this.password = generatePassword();
    }

    private String generatePassword() {
        StringBuilder randomPassword = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            randomPassword.append(CHARACTERS.charAt(randomIndex));
        }

        return randomPassword.toString();
    }
}
