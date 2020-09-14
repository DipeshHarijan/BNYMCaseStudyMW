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
		Customer cs = service.addCustomer(customer);
		if (cs != null) {
			return new ResponseEntity<>(cs, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/get/{pan}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String pan) {
		return new ResponseEntity<>(service.getCustomer(pan), HttpStatus.OK);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return new ResponseEntity<>(service.getAllCustomers(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{pan}")
	public ResponseEntity<String> deleteCustomer(@PathVariable String pan) {
		if (service.getCustomer(pan) != null) {
			service.deleteCustomer(pan);
			return new ResponseEntity<>("Customer has been deleted", HttpStatus.OK);
		}
		return new ResponseEntity<>("Customer doesn't exist", HttpStatus.NOT_FOUND);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
		if (service.getCustomer(customer.getPan()) != null) {
			return new ResponseEntity<>(service.updateCustomer(customer), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
