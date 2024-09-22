package com.hackathon.entities;

import com.hackathon.entities.dtos.DoctorRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DoctorTest {

    private Doctor doctor;
    private DoctorRequestDto doctorRequestDto;

    @BeforeEach
    public void setUp() {
        // Initialize a DoctorRequestDto for testing
        doctorRequestDto = new DoctorRequestDto("Dr. John Doe", "12345", "john.doe@example.com");
        doctor = new Doctor(doctorRequestDto);
    }

    @Test
    public void testDoctorConstructor() {
        // Ensure doctor is correctly initialized from DTO
        assertNotNull(doctor);
        assertEquals("Dr. John Doe", doctor.getName());
        assertEquals("12345", doctor.getCrm());
        assertEquals("john.doe@example.com", doctor.getEmail());
        assertNotNull(doctor.getPassword());
        assertEquals(10, doctor.getPassword().length());  // Ensure the password is 10 characters long
    }

    @Test
    public void testPasswordGeneration() {
        // Check if the generated password is 10 characters long and alphanumeric
        String password = doctor.generatePassword();
        assertNotNull(password);
        assertEquals(10, password.length());
        assertTrue(password.matches("[A-Za-z0-9]+"));  // Ensure the password only contains alphanumeric characters
    }

    @Test
    public void testAppointmentsAssociation() {
        // Ensure that the appointments list is initially empty
        List<Appointment> appointments = doctor.getAppointments();
        assertNotNull(appointments);
        assertTrue(appointments.isEmpty());

        // Add a new appointment and ensure it is added to the list
        Appointment appointment = new Appointment();
        appointments.add(appointment);
        doctor.setAppointments(appointments);
        assertEquals(1, doctor.getAppointments().size());
        assertEquals(appointment, doctor.getAppointments().get(0));
    }
}
