package com.teamtwo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages =  "com.teamtwo")
public class GuessNumberAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuessNumberAssignmentApplication.class, args);
	}

}
