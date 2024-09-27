package com.hackathon.entities.dtos;

import com.hackathon.entities.dtos.GetDoctorByCrmRequestDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GetDoctorByCrmRequestDtoTest {

    @Test
    public void testGetDoctorByCrmRequestDtoCreation() {
        // Given: A sample CRM value
        String crm = "CRM12345";

        // When: Creating a GetDoctorByCrmRequestDto object
        GetDoctorByCrmRequestDto getDoctorByCrmRequestDto = new GetDoctorByCrmRequestDto(crm);

        // Then: Ensure the CRM value is correctly set
        assertEquals(crm, getDoctorByCrmRequestDto.crm());
    }

    @Test
    public void testNullCrm() {
        // Given: A null CRM value
        String crm = null;

        // When: Creating a GetDoctorByCrmRequestDto object with null CRM
        GetDoctorByCrmRequestDto getDoctorByCrmRequestDto = new GetDoctorByCrmRequestDto(crm);

        // Then: Ensure the CRM value is null
        assertNull(getDoctorByCrmRequestDto.crm());
    }
}
