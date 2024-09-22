package com.hackathon.services;

import com.hackathon.entities.Appointment;
import com.hackathon.entities.Doctor;
import com.hackathon.entities.Patient;
import com.hackathon.entities.dtos.AppointmentRequestDto;
import com.hackathon.entities.dtos.AppointmentSetPatientRequestDto;
import com.hackathon.repositories.AppointmentRepository;
import com.hackathon.services.AppointmentService;
import com.hackathon.services.DoctorService;
import com.hackathon.services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private PatientService patientService;

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private AppointmentService appointmentService;

    private AppointmentRequestDto appointmentRequestDto;
    private AppointmentSetPatientRequestDto appointmentSetPatientRequestDto;
    private Doctor doctor;
    private Appointment appointment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        appointmentRequestDto = new AppointmentRequestDto("54321", 2024, 9, 23, 10, 00);
        appointmentSetPatientRequestDto = new AppointmentSetPatientRequestDto("1", "12345678900");
        doctor = new Doctor();
        doctor.setCrm("54321");
        appointment = new Appointment(appointmentRequestDto, doctor);
    }

    @Test
    public void testCreateAppointment() {
        // Mock the doctor retrieval and appointment save methods
        when(doctorService.findByCrm("54321")).thenReturn(doctor);
        when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

        // Call the service to create an appointment
        Appointment createdAppointment = appointmentService.createAppointment(appointmentRequestDto);

        // Verify that the appointment was saved and assert values
        verify(doctorService, times(1)).findByCrm("54321");
        verify(appointmentRepository, times(1)).save(any(Appointment.class));
        assertNotNull(createdAppointment);
        assertEquals("54321", createdAppointment.getDoctor().getCrm());
    }

    @Test
    public void testListAppointments() {
        // Mock the findAll method to return a list of appointments
        List<Appointment> appointments = Arrays.asList(appointment);
        when(appointmentRepository.findAll()).thenReturn(appointments);

        // Call the service to list appointments
        List<Appointment> result = appointmentService.listAppointments();

        // Verify that the repository's findAll method was called
        verify(appointmentRepository, times(1)).findAll();
        assertEquals(1, result.size());
        assertEquals(appointment, result.get(0));
    }

    @Test
    public void testGetAppointmentById() {
        // Mock the findById method to return an appointment
        when(appointmentRepository.findById("1")).thenReturn(Optional.of(appointment));

        // Call the service to get an appointment by ID
        Optional<Appointment> result = appointmentService.getAppointmentById("1");

        // Verify that the repository's findById method was called
        verify(appointmentRepository, times(1)).findById("1");
        assertTrue(result.isPresent());
        assertEquals(appointment, result.get());
    }

    @Test
    public void testSetPatientToAppointment() throws Exception {
        // Mock the patient and appointment retrieval methods
        when(appointmentRepository.findById("1")).thenReturn(Optional.of(appointment));
        when(patientService.findByCpf("12345678900")).thenReturn(new Patient());

        // Call the service to set a patient to the appointment
        Optional<Appointment> updatedAppointment = appointmentService.setPatientToAppointment(appointmentSetPatientRequestDto);

        // Verify that the appointment was updated and saved
        verify(appointmentRepository, times(1)).save(appointment);
        assertTrue(updatedAppointment.isPresent());
        assertTrue(updatedAppointment.get().isBooked());
    }
}
