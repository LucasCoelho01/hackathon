package com.hackathon.services;

import com.hackathon.entities.Patient;
import com.hackathon.entities.dtos.PatientRequestDto;
import com.hackathon.repositories.PatientRepository;
import com.hackathon.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private PatientRequestDto patientRequestDto;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        patientRequestDto = new PatientRequestDto("Jane Doe", "12345678900", "jane.doe@example.com");
        patient = new Patient(patientRequestDto);
    }

    @Test
    public void testCreatePatient() {
        // Mock the save method in the repository
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        // Call the service to create a patient
        Patient createdPatient = patientService.createPatient(patientRequestDto);

        // Verify that the patient was saved and assert values
        verify(patientRepository, times(1)).save(any(Patient.class));
        assertEquals("Jane Doe", createdPatient.getName());
        assertEquals("12345678900", createdPatient.getCpf());
        assertEquals("jane.doe@example.com", createdPatient.getEmail());
    }

    @Test
    public void testListPatients() {
        // Mock the findAll method to return a list of patients
        List<Patient> patients = Arrays.asList(patient);
        when(patientRepository.findAll()).thenReturn(patients);

        // Call the service to list patients
        List<Patient> result = patientService.listPatients();

        // Verify that the repository's findAll method was called
        verify(patientRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(patient, result.get(0));
    }

    @Test
    public void testFindByCpf() {
        // Mock the findByCpf method to return a specific patient
        when(patientRepository.findByCpf("12345678900")).thenReturn(patient);

        // Call the service to find a patient by CPF
        Patient result = patientService.findByCpf("12345678900");

        // Verify that the repository's findByCpf method was called
        verify(patientRepository, times(1)).findByCpf("12345678900");
        assertNotNull(result);
        assertEquals("12345678900", result.getCpf());
        assertEquals("Jane Doe", result.getName());
    }
}
