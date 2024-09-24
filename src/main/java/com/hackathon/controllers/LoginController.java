package com.hackathon.controllers;

import com.hackathon.entities.User;
import com.hackathon.entities.dtos.LoginDto;
import com.hackathon.entities.dtos.ResponseTokenJwt;
import com.hackathon.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    ResponseEntity<ResponseTokenJwt> login(@RequestBody LoginDto loginDto) throws Exception {
        var authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.login(), loginDto.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJwt = tokenService.generateToken((User) authentication.getPrincipal());

        return new ResponseEntity<>(new ResponseTokenJwt(tokenJwt), HttpStatus.OK);
    }
}
