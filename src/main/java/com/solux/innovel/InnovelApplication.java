package com.solux.innovel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class InnovelApplication {

	public static void main(String[] args) {
		SpringApplication.run(InnovelApplication.class, args);
	}

}
