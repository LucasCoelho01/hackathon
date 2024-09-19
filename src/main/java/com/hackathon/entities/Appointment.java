package com.hackathon.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
}
