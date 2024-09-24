package com.hackathon.services;

import com.hackathon.entities.User;
import com.hackathon.repositories.UserRepository;
import com.hackathon.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void createUser(String login,  String password) {
        String encriptedPasswd=bCryptPasswordEncoder.encode(password);

        userRepository.save(new User(login, encriptedPasswd));
    }
}
