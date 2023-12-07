package com.secure.jwtimplementation;

import com.secure.jwtimplementation.helper.RefreshableCRUDRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(repositoryBaseClass = RefreshableCRUDRepositoryImpl.class)
@SpringBootApplication
public class JwtImplementationApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtImplementationApplication.class, args);
	}

}
