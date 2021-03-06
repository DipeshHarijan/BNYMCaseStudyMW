package com.cts.bnym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bnym.entity.Customer;
import com.cts.bnym.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repo;

	public Customer addCustomer(Customer customer) {
		if (getCustomer(customer.getPan()) == null) {
			return repo.save(customer);
		}
		return null;
	}

	public List<Customer> getAllCustomers() {
		return (List<Customer>) repo.findAll();
	}

	public Customer getCustomer(String pan) {
		Customer customer = new Customer();
		customer = repo.findById(pan).orElse(null);
		return customer;
	}

	public void deleteCustomer(String pan) {
		repo.deleteById(pan);
	}

	public Customer updateCustomer(Customer customer) {
		return repo.save(customer);
	}

}
