package com.technlolab.commandes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Active OpenFeign
public class ServiceCommandesApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceCommandesApplication.class, args);
	}

}
