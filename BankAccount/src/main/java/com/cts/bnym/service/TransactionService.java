package com.cts.bnym.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private BankAccountService accountService;

	@Autowired
	private MutualFundProxy mfp;

	public Transaction invest(Transaction transaction) {
		MutualFund fund = mfp.get(transaction.getMutualFundId()).getBody();
		Account account = accountService.getAccount(transaction.getAccount().getAccountNumber());
		if (fund != null && account != null) {
			transaction.setTransactionId(10000000000L + new Random().nextInt(900000000));
			transaction.setTime(LocalDateTime.now());
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
		accounts.stream().forEach(account -> transactions.addAll(repo.findAllByAccount(account)));
		return transactions;
	}

}
