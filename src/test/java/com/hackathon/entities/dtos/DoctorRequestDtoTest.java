package com.hackathon.entities.dtos;

import com.hackathon.entities.dtos.DoctorRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorRequestDtoTest {

    @Test
    public void testDoctorRequestDtoCreation() {
        // Given: Sample data for the DoctorRequestDto
        String name = "Dr. John Doe";
        String crm = "CRM12345";
        String email = "john.doe@example.com";

        // When: Creating a DoctorRequestDto object
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto(name, crm, email);

        // Then: Ensure all values are correctly set
        assertEquals(name, doctorRequestDto.name());
        assertEquals(crm, doctorRequestDto.crm());
        assertEquals(email, doctorRequestDto.email());
    }

    @Test
    public void testNullName() {
        // Given: Null name and valid crm and email
        String name = null;
        String crm = "CRM12345";
        String email = "john.doe@example.com";

        // When: Creating a DoctorRequestDto object with null name
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto(name, crm, email);

        // Then: Ensure that name is null and other values are correct
        assertNull(doctorRequestDto.name());
        assertEquals(crm, doctorRequestDto.crm());
        assertEquals(email, doctorRequestDto.email());
    }

    @Test
    public void testNullCrm() {
        // Given: Valid name and email, but null crm
        String name = "Dr. John Doe";
        String crm = null;
        String email = "john.doe@example.com";

        // When: Creating a DoctorRequestDto object with null crm
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto(name, crm, email);

        // Then: Ensure that crm is null and other values are correct
        assertEquals(name, doctorRequestDto.name());
        assertNull(doctorRequestDto.crm());
        assertEquals(email, doctorRequestDto.email());
    }

    @Test
    public void testNullEmail() {
        // Given: Valid name and crm, but null email
        String name = "Dr. John Doe";
        String crm = "CRM12345";
        String email = null;

        // When: Creating a DoctorRequestDto object with null email
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto(name, crm, email);

        // Then: Ensure that email is null and other values are correct
        assertEquals(name, doctorRequestDto.name());
        assertEquals(crm, doctorRequestDto.crm());
        assertNull(doctorRequestDto.email());
    }

    @Test
    public void testAllNull() {
        // Given: All values are null
        String name = null;
        String crm = null;
        String email = null;

        // When: Creating a DoctorRequestDto object with all null values
        DoctorRequestDto doctorRequestDto = new DoctorRequestDto(name, crm, email);

        // Then: Ensure all values are null
        assertNull(doctorRequestDto.name());
        assertNull(doctorRequestDto.crm());
        assertNull(doctorRequestDto.email());
    }
}
