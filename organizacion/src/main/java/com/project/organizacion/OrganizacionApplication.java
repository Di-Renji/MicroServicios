package com.project.organizacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class OrganizacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganizacionApplication.class, args);
	}

}
