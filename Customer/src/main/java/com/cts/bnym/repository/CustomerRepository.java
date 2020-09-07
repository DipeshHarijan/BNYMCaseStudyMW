package com.cts.bnym.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.bnym.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {

}
