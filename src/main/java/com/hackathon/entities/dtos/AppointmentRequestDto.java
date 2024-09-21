package com.hackathon.entities.dtos;

public record AppointmentRequestDto(
        String doctorCrm,
        int year,
        int month,
        int day,
        int hour,
        int minute
) {
}
