package com.example.hrms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class HrmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrmsApplication.class, args);
	}

	@Bean
	public CommandLineRunner testPassword(
			PasswordEncoder passwordEncoder) {

		return args -> {
			System.out.println(
					"BCrypt Password = "
							+ passwordEncoder.encode("123456"));
		};
	}
}
