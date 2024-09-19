package com.hackathon.entities;

import com.hackathon.entities.dtos.DoctorRequestDto;
import jakarta.persistence.*;
import lombok.Data;

import java.security.SecureRandom;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String crm;
    private String email;
    private String password;

    public Doctor(){}

    public Doctor(DoctorRequestDto doctorRequestDto) {
        this.name = doctorRequestDto.name();
        this.crm = doctorRequestDto.crm();
        this.name = doctorRequestDto.email();
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
