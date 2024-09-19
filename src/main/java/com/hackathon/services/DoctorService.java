package com.hackathon.services;

import com.hackathon.entities.Doctor;
import com.hackathon.entities.dtos.DoctorRequestDto;
import com.hackathon.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Transactional
    public Doctor createDoctor(DoctorRequestDto doctorRequestDto) {
        Doctor doctor = new Doctor(doctorRequestDto);

        return doctorRepository.save(doctor);
    }

    public List<Doctor> listDoctors() {
        return doctorRepository.findAll();
    }
}
