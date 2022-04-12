package com.contact.urbancontact;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UrbanContactApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrbanContactApplication.class, args);
	}

}
