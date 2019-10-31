package com.insert.company.name.productsbackend;

import com.insert.company.name.productsbackend.repositories.PackageRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ProductsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsBackendApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public PackageRepository packageRepository() {
		return new PackageRepository();
	}
}
