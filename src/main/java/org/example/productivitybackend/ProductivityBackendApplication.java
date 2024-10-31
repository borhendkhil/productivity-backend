package org.example.productivitybackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
@EnableMongoRepositories(basePackages = "org.example.productivitybackend.repository")
public class ProductivityBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductivityBackendApplication.class, args);
	}

}
