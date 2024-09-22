package com.hackathon.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hackathon.entities.dtos.DoctorRequestDto;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "doctors")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String crm;
    private String email;
    private String password;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(){}

    public Doctor(DoctorRequestDto doctorRequestDto) {
        this.name = doctorRequestDto.name();
        this.crm = doctorRequestDto.crm();
        this.email = doctorRequestDto.email();
        this.password = generatePassword();
    }

    public String generatePassword() {
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
