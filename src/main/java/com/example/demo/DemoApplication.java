package com.example.demo;

import com.example.demo.auth.AuthenticationService;
import com.example.demo.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.demo.user.Role.ADMIN;
import static com.example.demo.user.Role.USER;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var admin = RegisterRequest.builder()
					.firstname("Ayaan")
					.lastname("Sharma")
					.email("ayaan@gmail.com")
					.password("sharma@259")
					.role(ADMIN)
					.build();

			System.out.println("Admin token: " + service.register(admin).getToken());
			var user = RegisterRequest.builder()
					.firstname("Raj")
					.lastname("Aryan")
					.email("raj@gmail.com")
					.password("raj@259")
					.role(USER)
					.build();
			System.out.println("User token: " + service.register(user).getToken());

		};
	}

}
