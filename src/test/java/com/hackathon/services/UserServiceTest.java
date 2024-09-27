package com.hackathon.services;

import com.hackathon.entities.User;
import com.hackathon.repositories.UserRepository;
import com.hackathon.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {
        // Given: a login and password
        String login = "testUser";
        String rawPassword = "password123";
        String encodedPassword = "encodedPassword";

        // When: Encoding password and creating the user
        when(passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);
        userService.createUser(login, rawPassword);

        // Then: The password should be encoded and the user should be saved
        verify(passwordEncoder, times(1)).encode(rawPassword);
        verify(userRepository, times(1)).save(any(User.class));

        // Additionally, verify the user is saved with the correct login and encoded password
        verify(userRepository).save(argThat(user ->
                user.getLogin().equals(login) &&
                        user.getPassword().equals(encodedPassword)
        ));
    }
}
