package com.hackathon.entities.dtos;

import com.hackathon.entities.dtos.AppointmentSetPatientRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentSetPatientRequestDtoTest {

    @Test
    public void testAppointmentSetPatientRequestDtoCreation() {
        // Given: Sample data for appointmentId and patientCpf
        String appointmentId = "appt123";
        String patientCpf = "12345678901";

        // When: Creating an AppointmentSetPatientRequestDto object
        AppointmentSetPatientRequestDto appointmentSetPatientRequestDto = new AppointmentSetPatientRequestDto(appointmentId, patientCpf);

        // Then: Ensure all values are correctly set
        assertEquals(appointmentId, appointmentSetPatientRequestDto.appointmentId());
        assertEquals(patientCpf, appointmentSetPatientRequestDto.patientCpf());
    }

    @Test
    public void testNullAppointmentId() {
        // Given: Null appointmentId and valid patientCpf
        String appointmentId = null;
        String patientCpf = "12345678901";

        // When: Creating an AppointmentSetPatientRequestDto object with null appointmentId
        AppointmentSetPatientRequestDto appointmentSetPatientRequestDto = new AppointmentSetPatientRequestDto(appointmentId, patientCpf);

        // Then: Ensure that appointmentId is null and patientCpf is correct
        assertNull(appointmentSetPatientRequestDto.appointmentId());
        assertEquals(patientCpf, appointmentSetPatientRequestDto.patientCpf());
    }

    @Test
    public void testNullPatientCpf() {
        // Given: Valid appointmentId and null patientCpf
        String appointmentId = "appt123";
        String patientCpf = null;

        // When: Creating an AppointmentSetPatientRequestDto object with null patientCpf
        AppointmentSetPatientRequestDto appointmentSetPatientRequestDto = new AppointmentSetPatientRequestDto(appointmentId, patientCpf);

        // Then: Ensure that patientCpf is null and appointmentId is correct
        assertEquals(appointmentId, appointmentSetPatientRequestDto.appointmentId());
        assertNull(appointmentSetPatientRequestDto.patientCpf());
    }

    @Test
    public void testBothNull() {
        // Given: Both appointmentId and patientCpf are null
        String appointmentId = null;
        String patientCpf = null;

        // When: Creating an AppointmentSetPatientRequestDto object with both null values
        AppointmentSetPatientRequestDto appointmentSetPatientRequestDto = new AppointmentSetPatientRequestDto(appointmentId, patientCpf);

        // Then: Ensure both appointmentId and patientCpf are null
        assertNull(appointmentSetPatientRequestDto.appointmentId());
        assertNull(appointmentSetPatientRequestDto.patientCpf());
    }
}
