package com.jzhl.frame01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableScheduling
public class Frame01Application {

    public static void main(String[] args) {
        SpringApplication.run(Frame01Application.class, args);
    }

}
