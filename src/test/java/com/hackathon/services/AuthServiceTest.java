package com.hackathon.services;

import com.hackathon.entities.User;
import com.hackathon.repositories.UserRepository;
import com.hackathon.services.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_UserFound() {
        // Given: A username and a mock user
        String username = "testUser";
        User mockUser = new User(username, "password123");

        // When: The userRepository returns a user
        when(userRepository.findByLogin(username)).thenReturn(mockUser);

        // Then: The user should be returned by the authService
        UserDetails userDetails = authService.loadUserByUsername(username);
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());

        // Verify that userRepository.findByLogin was called once with the correct username
        verify(userRepository, times(1)).findByLogin(username);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Given: A username that doesn't exist
        String username = "nonExistentUser";

        // When: The userRepository returns null
        when(userRepository.findByLogin(username)).thenReturn(null);

        authService.loadUserByUsername(username);
    }
}
