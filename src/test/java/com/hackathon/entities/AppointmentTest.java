package com.hackathon.entities;

import static org.junit.jupiter.api.Assertions.*;

import com.hackathon.entities.dtos.AppointmentRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

class AppointmentTest {

    private Doctor mockDoctor;
    private AppointmentRequestDto mockAppointmentRequestDto;
    private Appointment appointment;

    @BeforeEach
    void setUp() throws Exception {
        mockDoctor = Mockito.mock(Doctor.class);
        mockAppointmentRequestDto = Mockito.mock(AppointmentRequestDto.class);

        Mockito.when(mockAppointmentRequestDto.year()).thenReturn(2025);
        Mockito.when(mockAppointmentRequestDto.month()).thenReturn(9);
        Mockito.when(mockAppointmentRequestDto.day()).thenReturn(22);
        Mockito.when(mockAppointmentRequestDto.hour()).thenReturn(10);
        Mockito.when(mockAppointmentRequestDto.minute()).thenReturn(0);

        appointment = new Appointment(mockAppointmentRequestDto, mockDoctor);
    }

    @Test
    void testConstructor() {
        assertNotNull(appointment.getDoctor());
        assertEquals("22-09-2025 10:00", appointment.getInitialDateTime());
        assertEquals("22-09-2025 11:00", appointment.getFinalDateTime());
    }

    @Test
    void testConvertInitialLocalDateTime() throws Exception {
        String expectedInitialDateTime = "22-09-2025 10:00";
        String actualInitialDateTime = appointment.convertInitialLocalDateTime(2025, 9, 22, 10, 0);
        assertEquals(expectedInitialDateTime, actualInitialDateTime);
    }

    @Test
    void testConvertFinalLocalDateTime() {
        LocalDateTime initialDateTime = LocalDateTime.of(2025, 9, 22, 10, 0);
        String expectedFinalDateTime = "22-09-2025 11:00";
        String actualFinalDateTime = appointment.convertFinalLocalDateTime(initialDateTime);
        assertEquals(expectedFinalDateTime, actualFinalDateTime);
    }
}
