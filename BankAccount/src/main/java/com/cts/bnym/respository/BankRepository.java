package com.cts.bnym.respository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.bnym.entity.Bank;

@Repository
public interface BankRepository extends CrudRepository<Bank, String> {

}
