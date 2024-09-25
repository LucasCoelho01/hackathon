package com.hackathon.security;

import com.hackathon.repositories.UserRepository;
import com.hackathon.security.SecurityFilter;
import com.hackathon.security.TokenService;
import com.hackathon.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SecurityFilterTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private SecurityFilter securityFilter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext(); // Clear the SecurityContext before each test
    }

    @Test
    public void testDoFilterInternal_withValidToken_shouldSetAuthentication() throws ServletException, IOException {
        // Given: A valid JWT token in the Authorization header
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer valid.jwt.token");
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Mocking the tokenService and userRepository behavior
        String subject = "testUser";
        when(tokenService.getSubject("valid.jwt.token")).thenReturn(subject);

        User user = new User("testUser", "password");
        when(userRepository.findByLogin(subject)).thenReturn(user);

        // When: Executing the filter
        securityFilter.doFilterInternal(request, response, filterChain);

        // Then: The SecurityContext should have the correct authentication set
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNotNull(authentication);
        assertEquals(authentication.getPrincipal(), user);
        assertTrue(authentication instanceof UsernamePasswordAuthenticationToken);

        // Ensure the filter chain continues
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_withInvalidToken_shouldNotSetAuthentication() throws ServletException, IOException {
        // Given: No Authorization header or an invalid token
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // When: Executing the filter
        securityFilter.doFilterInternal(request, response, filterChain);

        // Then: The SecurityContext should not have any authentication
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        assertNull(authentication);

        // Ensure the filter chain continues
        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_withNullAuthorizationHeader() throws ServletException, IOException {
        // Given: A request without an Authorization header
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();

        // When: Executing the filter
        securityFilter.doFilterInternal(request, response, filterChain);

        // Then: Ensure no authentication is set and the filter chain proceeds
        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain).doFilter(request, response);
    }
}
