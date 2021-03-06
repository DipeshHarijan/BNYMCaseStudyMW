package com.cts.bnym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableSwagger2
public class MutualFundApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutualFundApplication.class, args);
	}

}
