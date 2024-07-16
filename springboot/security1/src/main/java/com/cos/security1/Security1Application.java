package com.cos.security1;

import com.fasterxml.jackson.datatype.hibernate5.jakarta.Hibernate5JakartaModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Security1Application {

	public static void main(String[] args) {
		SpringApplication.run(Security1Application.class, args);
	}

//	@Bean
//	Hibernate5JakartaModule hibernate5Module() {
//		Hibernate5JakartaModule module = new Hibernate5JakartaModule();
//		return module;
//	}
}
