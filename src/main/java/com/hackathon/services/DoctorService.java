package com.hackathon.services;

import com.hackathon.entities.Appointment;
import com.hackathon.entities.Doctor;
import com.hackathon.entities.dtos.DoctorRequestDto;
import com.hackathon.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Doctor createDoctor(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = new Doctor(doctorRequestDto);

        userService.createUser(doctor.getEmail(), doctor.getPassword());
        return doctorRepository.save(doctor);
    }

    public List<Doctor> listDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor findByCrm(String crm) {
        return doctorRepository.findByCrm(crm);
    }

    public List<Appointment> getDoctorsFreeAppointments(String crm) {
        Doctor doctor = doctorRepository.findByCrm(crm);
        List<Appointment> appointments= new ArrayList<>();

        doctor.getAppointments().forEach(appointment -> {
            if(!appointment.isBooked()) {
                appointments.add(appointment);
            }
        });

        return appointments;
    }
}
