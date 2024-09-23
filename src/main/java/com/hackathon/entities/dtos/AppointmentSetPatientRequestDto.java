package com.hackathon.entities.dtos;

public record AppointmentSetPatientRequestDto(
        String appointmentId,
        String patientCpf
) {
}
