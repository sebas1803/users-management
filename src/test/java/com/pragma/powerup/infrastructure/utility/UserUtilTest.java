package com.pragma.powerup.infrastructure.utility;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserUtilTest {
    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "password";
        String encodedPassword = UserUtil.encryptPassword(password);
        assertTrue(UserUtil.matchPassword(password, encodedPassword));
    }
}
