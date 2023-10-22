package com.app.nailcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NailCareApplication {

	public static void main(String[] args) {
		SpringApplication.run(NailCareApplication.class, args);
	}

}
