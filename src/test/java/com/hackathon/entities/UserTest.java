package com.hackathon.entities;

import com.hackathon.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        // Create a user instance before each test
        user = new User("testUser", "testPassword");
    }

    @Test
    public void testConstructor() {
        // Test if the constructor correctly sets login and password
        assertEquals("testUser", user.getUsername());
        assertEquals("testPassword", user.getPassword());
    }

    @Test
    public void testGetAuthorities() {
        // Test if the user has the correct authorities
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertNotNull(authorities);
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    public void testIsAccountNonExpired() {
        // Test if the account is non-expired
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        // Test if the account is non-locked
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        // Test if the credentials are non-expired
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        // Test if the account is enabled
        assertTrue(user.isEnabled());
    }
}
