package com.udemy.spring.cource.universityjava.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class EncoderUtil {

    public static void main(String[] args) {

        String password = "060900";
        BCryptPasswordEncoder  bCryptPasswordEncoder = new BCryptPasswordEncoder();
        log.info("060900 => {}", bCryptPasswordEncoder.encode(password));
    }

}
