package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // This is what starts the entire Spring context
public class PracticeProjectApplication {  // Class name should match your project

    public static void main(String[] args) {
        SpringApplication.run(PracticeProjectApplication.class, args);
    }
}
