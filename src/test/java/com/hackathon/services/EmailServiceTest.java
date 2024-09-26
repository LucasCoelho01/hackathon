package com.hackathon.services;

import com.hackathon.entities.Email;
import com.hackathon.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender mailSender;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendEmail() {
        // Given: an Email object
        Email email = new Email("test@example.com", "Test Subject", "Test body");

        // When: sending the email
        emailService.sendEmail(email);

        // Then: the mailSender should send the correct SimpleMailMessage
        SimpleMailMessage expectedMessage = new SimpleMailMessage();
        expectedMessage.setFrom("noreply@email.com");
        expectedMessage.setTo(email.to());
        expectedMessage.setSubject(email.subject());
        expectedMessage.setText(email.body());

        // Verify that mailSender.send() was called with the expected message
        verify(mailSender, times(1)).send(expectedMessage);
    }
}
