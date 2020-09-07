package com.cts.bnym;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	Docket configureSwagger() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/customer/**")).build()

				.apiInfo(new ApiInfo("Customer API Documentation", "A Sample API for Customers", "1.0.0", "Banking is Fun",
						new Contact("Dipesh", "www.dipeshcorporation.com", "dipesh@gmail.com"), "Standard API License",
						"www.dipesh.com", Collections.emptyList()));

	}
}
