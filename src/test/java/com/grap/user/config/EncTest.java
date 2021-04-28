package com.grap.user.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class EncTest {

    @Test // 비밀번호 해쉬 테스트
    public void hash_inc() {
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("1234 해쉬 : " + encPassword);
    }

}