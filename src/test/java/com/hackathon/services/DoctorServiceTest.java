package com.hackathon.services;

import com.hackathon.entities.Doctor;
import com.hackathon.entities.Appointment;
import com.hackathon.entities.dtos.DoctorRequestDto;
import com.hackathon.repositories.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private UserService userService;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private DoctorRequestDto doctorRequestDto;
    private Doctor doctor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        doctorRequestDto = new DoctorRequestDto("Dr. John Doe", "54321", "john.doe@example.com");
        doctor = new Doctor(doctorRequestDto);
    }

    @Test
    public void testCreateDoctor() {
        // Mock the save method in the repository
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        // Call the service to create a doctor
        Doctor createdDoctor = doctorService.createDoctor(doctorRequestDto);

        // Verify that the doctor was saved and assert values
        verify(doctorRepository, times(1)).save(any(Doctor.class));
        assertEquals("Dr. John Doe", createdDoctor.getName());
        assertEquals("54321", createdDoctor.getCrm());
        assertEquals("john.doe@example.com", createdDoctor.getEmail());
    }

    @Test
    public void testListDoctors() {
        // Mock the findAll method to return a list of doctors
        List<Doctor> doctors = Arrays.asList(doctor);
        when(doctorRepository.findAll()).thenReturn(doctors);

        // Call the service to list doctors
        List<Doctor> result = doctorService.listDoctors();

        // Verify that the repository's findAll method was called
        verify(doctorRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(doctor, result.get(0));
    }

    @Test
    public void testFindByCrm() {
        // Mock the findByCrm method to return a specific doctor
        when(doctorRepository.findByCrm("54321")).thenReturn(doctor);

        // Call the service to find a doctor by CRM
        Doctor result = doctorService.findByCrm("54321");

        // Verify that the repository's findByCrm method was called
        verify(doctorRepository, times(1)).findByCrm("54321");
        assertNotNull(result);
        assertEquals("54321", result.getCrm());
        assertEquals("Dr. John Doe", result.getName());
    }

    @Test
    public void testGetDoctorsFreeAppointments() {
        // Mock the getAppointments to return a list of appointments
        Appointment freeAppointment = new Appointment();
        freeAppointment.setBooked(false);  // Free appointment

        Appointment bookedAppointment = new Appointment();
        bookedAppointment.setBooked(true);  // Booked appointment

        List<Appointment> appointments = Arrays.asList(freeAppointment, bookedAppointment);
        doctor.setAppointments(appointments);

        // Mock the findByCrm method to return the doctor
        when(doctorRepository.findByCrm("54321")).thenReturn(doctor);

        // Call the service to get free appointments for a doctor
        List<Appointment> freeAppointments = doctorService.getDoctorsFreeAppointments("54321");

        // Verify that only free appointments are returned
        assertEquals(1, freeAppointments.size());
        assertFalse(freeAppointments.get(0).isBooked());
    }
}
