package com.hackathon.entities.dtos;

import com.hackathon.entities.dtos.PatientRequestDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PatientRequestDtoTest {

    @Test
    public void testPatientRequestDtoCreation() {
        // Given: Sample values for the PatientRequestDto
        String name = "Jane Doe";
        String cpf = "12345678900";
        String email = "jane.doe@example.com";

        // When: Creating a PatientRequestDto object
        PatientRequestDto patientRequestDto = new PatientRequestDto(name, cpf, email);

        // Then: Ensure all values are correctly set
        assertEquals(name, patientRequestDto.name());
        assertEquals(cpf, patientRequestDto.cpf());
        assertEquals(email, patientRequestDto.email());
    }

    @Test
    public void testNullName() {
        // Given: Null name and valid cpf and email
        String name = null;
        String cpf = "12345678900";
        String email = "jane.doe@example.com";

        // When: Creating a PatientRequestDto object with null name
        PatientRequestDto patientRequestDto = new PatientRequestDto(name, cpf, email);

        // Then: Ensure the name is null and other values are correct
        assertNull(patientRequestDto.name());
        assertEquals(cpf, patientRequestDto.cpf());
        assertEquals(email, patientRequestDto.email());
    }

    @Test
    public void testNullCpf() {
        // Given: Valid name and email, but null cpf
        String name = "Jane Doe";
        String cpf = null;
        String email = "jane.doe@example.com";

        // When: Creating a PatientRequestDto object with null cpf
        PatientRequestDto patientRequestDto = new PatientRequestDto(name, cpf, email);

        // Then: Ensure the cpf is null and other values are correct
        assertEquals(name, patientRequestDto.name());
        assertNull(patientRequestDto.cpf());
        assertEquals(email, patientRequestDto.email());
    }

    @Test
    public void testNullEmail() {
        // Given: Valid name and cpf, but null email
        String name = "Jane Doe";
        String cpf = "12345678900";
        String email = null;

        // When: Creating a PatientRequestDto object with null email
        PatientRequestDto patientRequestDto = new PatientRequestDto(name, cpf, email);

        // Then: Ensure the email is null and other values are correct
        assertEquals(name, patientRequestDto.name());
        assertEquals(cpf, patientRequestDto.cpf());
        assertNull(patientRequestDto.email());
    }

    @Test
    public void testAllNull() {
        // Given: All values are null
        String name = null;
        String cpf = null;
        String email = null;

        // When: Creating a PatientRequestDto object with all null values
        PatientRequestDto patientRequestDto = new PatientRequestDto(name, cpf, email);

        // Then: Ensure all values are null
        assertNull(patientRequestDto.name());
        assertNull(patientRequestDto.cpf());
        assertNull(patientRequestDto.email());
    }
}
