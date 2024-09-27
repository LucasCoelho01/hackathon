package com.hackathon.entities.dtos;

import com.hackathon.entities.dtos.LoginDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginDtoTest {

    @Test
    public void testLoginDtoCreation() {
        // Given: Sample login and password values
        String login = "user123";
        String password = "password123";

        // When: Creating a LoginDto object
        LoginDto loginDto = new LoginDto(login, password);

        // Then: Ensure the login and password values are correctly set
        assertEquals(login, loginDto.login());
        assertEquals(password, loginDto.password());
    }

    @Test
    public void testNullLogin() {
        // Given: Null login and valid password
        String login = null;
        String password = "password123";

        // When: Creating a LoginDto object with null login
        LoginDto loginDto = new LoginDto(login, password);

        // Then: Ensure the login is null and password is correctly set
        assertNull(loginDto.login());
        assertEquals(password, loginDto.password());
    }

    @Test
    public void testNullPassword() {
        // Given: Valid login and null password
        String login = "user123";
        String password = null;

        // When: Creating a LoginDto object with null password
        LoginDto loginDto = new LoginDto(login, password);

        // Then: Ensure the password is null and login is correctly set
        assertEquals(login, loginDto.login());
        assertNull(loginDto.password());
    }

    @Test
    public void testBothNull() {
        // Given: Both login and password are null
        String login = null;
        String password = null;

        // When: Creating a LoginDto object with both null values
        LoginDto loginDto = new LoginDto(login, password);

        // Then: Ensure both login and password are null
        assertNull(loginDto.login());
        assertNull(loginDto.password());
    }
}
