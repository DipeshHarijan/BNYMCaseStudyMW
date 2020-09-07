package com.cts.bnym.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.bnym.entity.Customer;
import com.cts.bnym.service.CustomerService;

@CrossOrigin
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping(value = "/add")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {

		return new ResponseEntity<>(service.addCustomer(customer), HttpStatus.OK);
	}

	@GetMapping(value = "/get/{pan}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String pan) {
		return new ResponseEntity<Customer>(service.getCustomer(pan), HttpStatus.OK);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<List<Customer>>(service.getAllCustomers(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{pan}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String pan) {
		if (service.getCustomer(pan) != null) {
			service.deleteCustomer(pan);
			return new ResponseEntity<String>("Customer has been deleted", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Customer doesn't exist", HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<String> updateCustomer(@Valid @RequestBody Customer customer) {
		if (service.getCustomer(customer.getPan()) != null) {
			service.updateCustomer(customer);
			return new ResponseEntity<String>("Customer details have been updated", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Customer not found", HttpStatus.NOT_FOUND);
	}
}
