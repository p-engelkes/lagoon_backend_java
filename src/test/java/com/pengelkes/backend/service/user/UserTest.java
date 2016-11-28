package com.pengelkes.backend.service.user;

import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by pengelkes on 28.11.2016.
 */
public class UserTest
{
    @Test
    public void fromJson() throws IOException
    {
        String jsonString = "{\"firstName\":\"test\",\"lastName\":\"test\",\"userName\":\"admin@fake.com\",\"password\":\"test\"}";
        Optional<User> userOptional = User.fromJson(jsonString);
        assertTrue(userOptional.isPresent());
        assertEquals(userOptional.get(), new User("admin@fake.com", "test", "test", "test"));
    }
}