package com.shopme.shopmebackend.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.Assertions.*;

public class PasswordEncoderTest {

    @Test
    public void testPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "password";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        boolean isMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        assertThat(isMatch).isTrue();
    }
}
