package com.hackathon.entities.dtos;

import com.hackathon.entities.dtos.AppointmentRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentRequestDtoTest {

    @Test
    public void testAppointmentRequestDtoCreation() {
        // Given: Sample data to create an AppointmentRequestDto
        String doctorCrm = "123456";
        int year = 2024;
        int month = 9;
        int day = 23;
        int hour = 10;
        int minute = 30;

        // When: Creating an AppointmentRequestDto object
        AppointmentRequestDto appointmentRequestDto = new AppointmentRequestDto(doctorCrm, year, month, day, hour, minute);

        // Then: Ensure all values are correctly set
        assertEquals(doctorCrm, appointmentRequestDto.doctorCrm());
        assertEquals(year, appointmentRequestDto.year());
        assertEquals(month, appointmentRequestDto.month());
        assertEquals(day, appointmentRequestDto.day());
        assertEquals(hour, appointmentRequestDto.hour());
        assertEquals(minute, appointmentRequestDto.minute());
    }

    @Test
    public void testNullDoctorCrm() {
        // Given: Null doctorCrm and valid date/time data
        String doctorCrm = null;
        int year = 2024;
        int month = 9;
        int day = 23;
        int hour = 10;
        int minute = 30;

        // When: Creating an AppointmentRequestDto object with null doctorCrm
        AppointmentRequestDto appointmentRequestDto = new AppointmentRequestDto(doctorCrm, year, month, day, hour, minute);

        // Then: Ensure that doctorCrm is null and other values are correct
        assertNull(appointmentRequestDto.doctorCrm());
        assertEquals(year, appointmentRequestDto.year());
        assertEquals(month, appointmentRequestDto.month());
        assertEquals(day, appointmentRequestDto.day());
        assertEquals(hour, appointmentRequestDto.hour());
        assertEquals(minute, appointmentRequestDto.minute());
    }
}
