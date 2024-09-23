package com.hackathon.entities;

import com.hackathon.entities.dtos.PatientRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PatientTest {

    private Patient patient;
    private PatientRequestDto patientRequestDto;

    @BeforeEach
    public void setUp() {
        // Initialize a PatientRequestDto for testing
        patientRequestDto = new PatientRequestDto("Jane Doe", "12345678900", "jane.doe@example.com");
        patient = new Patient(patientRequestDto);
    }

    @Test
    public void testPatientConstructor() {
        // Ensure patient is correctly initialized from DTO
        assertNotNull(patient);
        assertEquals("Jane Doe", patient.getName());
        assertEquals("12345678900", patient.getCpf());
        assertEquals("jane.doe@example.com", patient.getEmail());
        assertNotNull(patient.getPassword());
        assertEquals(10, patient.getPassword().length());  // Ensure the password is 10 characters long
    }

    @Test
    public void testPasswordGeneration() {
        // Check if the generated password is 10 characters long and alphanumeric
        String password = patient.generatePassword();
        assertNotNull(password);
        assertEquals(10, password.length());
        assertTrue(password.matches("[A-Za-z0-9]+"));  // Ensure the password only contains alphanumeric characters
    }
}
