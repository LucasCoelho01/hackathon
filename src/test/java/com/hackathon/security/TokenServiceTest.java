package com.hackathon.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hackathon.entities.User;
import com.hackathon.security.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private User mockUser;

    private String secret = "testSecret";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Inject the secret value into the tokenService via reflection
        ReflectionTestUtils.setField(tokenService, "secret", secret);
    }

    @Test
    public void testGenerateToken() {
        // Given: A mock user with a login
        when(mockUser.getLogin()).thenReturn("testUser");

        // When: Generating a token
        String token = tokenService.generateToken(mockUser);

        // Then: The token should not be null or empty
        assertNotNull(token);
        assertFalse(token.isEmpty());

        // Validate that the token contains the correct subject (username)
        String subject = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("hackathon")
                .build()
                .verify(token)
                .getSubject();
        assertEquals("testUser", subject);
    }

    @Test
    public void testGetSubject_validToken() {
        // Given: A valid token with a known subject
        String token = JWT.create()
                .withIssuer("hackathon")
                .withSubject("testUser")
                .withExpiresAt(tokenService.getExpirationDate())
                .sign(Algorithm.HMAC256(secret));

        // When: Extracting the subject from the token
        String subject = tokenService.getSubject(token);

        // Then: The subject should be correctly retrieved
        assertEquals("testUser", subject);
    }

    @Test
    public void testGetSubject_invalidToken_shouldThrowException() {
        // Given: An invalid or malformed token
        String invalidToken = "invalid.token.value";

        // When/Then: Attempting to extract the subject should throw an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tokenService.getSubject(invalidToken);
        });

        assertEquals("Token JWT inválido ou expirado!", exception.getMessage());
    }

    @Test
    public void testGenerateTokenWithInvalidSecret_shouldThrowException() {
        // Given: An invalid secret value (empty secret)
        ReflectionTestUtils.setField(tokenService, "secret", "");

        // When/Then: Attempting to generate a token should throw an exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            tokenService.generateToken(mockUser);
        });
    }
}
