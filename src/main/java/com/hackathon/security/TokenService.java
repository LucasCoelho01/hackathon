package com.hackathon.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.hackathon.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("hackathon")
                    .withSubject(user.getLogin())
                    .withExpiresAt(getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Token error");
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("hackathon")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (Exception exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!");
        }
    }

    Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}
