package com.cts.bnym.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.bnym.entity.Account;
import com.cts.bnym.entity.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	List<Transaction> findAllByNumber(Account account);

}
