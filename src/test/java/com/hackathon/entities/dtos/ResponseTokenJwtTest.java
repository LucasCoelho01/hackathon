package com.hackathon.entities.dtos;

import com.hackathon.entities.dtos.ResponseTokenJwt;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResponseTokenJwtTest {

    @Test
    public void testResponseTokenJwtCreation() {
        // Given: A sample token value
        String token = "sample.jwt.token";

        // When: Creating a ResponseTokenJwt object
        ResponseTokenJwt responseTokenJwt = new ResponseTokenJwt(token);

        // Then: Ensure the token value is correctly set
        assertEquals(token, responseTokenJwt.token());
    }

    @Test
    public void testNullToken() {
        // Given: A null token value
        String token = null;

        // When: Creating a ResponseTokenJwt object with null token
        ResponseTokenJwt responseTokenJwt = new ResponseTokenJwt(token);

        // Then: Ensure the token is null
        assertNull(responseTokenJwt.token());
    }
}
