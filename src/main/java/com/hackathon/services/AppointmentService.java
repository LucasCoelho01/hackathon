package com.hackathon.services;

import com.hackathon.entities.Appointment;
import com.hackathon.entities.Doctor;
import com.hackathon.entities.Email;
import com.hackathon.entities.dtos.AppointmentRequestDto;
import com.hackathon.entities.dtos.AppointmentSetPatientRequestDto;
import com.hackathon.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private EmailService emailService;

    @Transactional
    public Appointment createAppointment(AppointmentRequestDto appointmentRequestDto) throws Exception {
        Doctor doctor = doctorService.findByCrm(appointmentRequestDto.doctorCrm());

        Appointment appointment = new Appointment(appointmentRequestDto, doctor);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> listAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    public Optional<Appointment> setPatientToAppointment(AppointmentSetPatientRequestDto appointmentSetPatientRequestDto) throws Exception {
        var appointment = getAppointmentById(appointmentSetPatientRequestDto.appointmentId());

        if (appointment.isPresent()) {
            appointment.get().setPatient(patientService.findByCpf(appointmentSetPatientRequestDto.patientCpf()));
            var updatedAppointment = appointment.get();
            updatedAppointment.setBooked(true);

            appointmentRepository.save(updatedAppointment);
            sendEmail(updatedAppointment);
        } else {
            new Exception("Appointment not found");
        }

        return appointment;
    }

    private void sendEmail(Appointment updatedAppointment) {
        String to = updatedAppointment.getDoctor().getEmail();
        String subject = "Health&Med - Nova consulta agendada";
        String body = "Olá, Dr. " + updatedAppointment.getDoctor().getName() + " !\n";
        body += "Você tem uma nova consulta marcada! \n";
        body += "Paciente: " + updatedAppointment.getPatient().getName() + "\n";
        body += "Data e horário: " + updatedAppointment.getInitialDateTime();

        var email = new Email(to, subject, body);

        emailService.sendEmail(email);

    }
}
