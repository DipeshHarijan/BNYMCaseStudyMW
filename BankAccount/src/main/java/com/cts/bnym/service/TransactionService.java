package com.cts.bnym.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bnym.entity.Account;
import com.cts.bnym.entity.Transaction;
import com.cts.bnym.model.MutualFund;
import com.cts.bnym.respository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repo;

	@Autowired
	private MutualFundProxy mfp;

	public Transaction invest(Transaction transaction) {
		MutualFund fund = mfp.get(transaction.getMutualFundId()).getBody();
		if (fund != null) {
			transaction.setTime(new Date());
			return repo.save(transaction);
		}
		return null;
	}

	public List<Transaction> getAll() {
		return (List<Transaction>) repo.findAll();
	}

	public List<Transaction> findAllByPan(List<Account> accounts) {
		List<Transaction> transactions = new ArrayList<>();
//		for (Account account : accounts) {
//			transactions.addAll(repo.findAllByNumber(account));
//		}
		accounts.stream().forEach(account -> transactions.addAll(repo.findAllByNumber(account)));
		return transactions;
	}

}