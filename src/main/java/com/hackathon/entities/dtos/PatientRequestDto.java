package com.hackathon.entities.dtos;

public record PatientRequestDto(
        String name,
        String cpf,
        String email
) {
}
