package com.cts.bnym.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.bnym.model.Customer;

@FeignClient(name = "customer-service")
public interface CustomerProxy {

	@GetMapping(value = "/customer/getall")
	ResponseEntity<List<Customer>> getAll();

	@GetMapping(value = "/customer/get/{pan}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String pan);

}
