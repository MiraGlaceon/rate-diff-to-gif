package com.mira.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RateDifferenceToGifApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateDifferenceToGifApplication.class, args);
	}

}
