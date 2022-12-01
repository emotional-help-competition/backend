package com.epam.emotionalhelp;

import com.epam.emotionalhelp.exception.ResourceNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmotionalHelpApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmotionalHelpApplication.class, args);

    }
}
