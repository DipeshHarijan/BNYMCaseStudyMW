package com.cts.bnym.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.bnym.entity.Account;
import com.cts.bnym.entity.Transaction;
import com.cts.bnym.service.BankAccountService;
import com.cts.bnym.service.TransactionService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
@RequestMapping(value = "/transaction")
public class TransactionController {

	@Autowired
	private TransactionService service;

	@Autowired
	private BankAccountService accountService;

	@HystrixCommand(fallbackMethod = "investFallback")
	@PostMapping(value = "/invest")
	public ResponseEntity<String> invest(@RequestBody Transaction transaction) {
		if (service.invest(transaction) != null) {
			return new ResponseEntity<String>("Investment successful", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Investment attempt failed. Try again.", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> investFallback(@RequestBody Transaction transaction) {
		return new ResponseEntity<String>("Server unreachable. Try again.", HttpStatus.SERVICE_UNAVAILABLE);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Transaction>> getAll() {
		return new ResponseEntity<List<Transaction>>(service.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/getInvestments/{pan}")
	public ResponseEntity<List<Transaction>> getTransactionsByPan(@PathVariable String pan) {
		List<Account> accounts = accountService.getByPan(pan);
		return new ResponseEntity<List<Transaction>>(service.findAllByPan(accounts), HttpStatus.OK);
	}

	@GetMapping(value = "/getInvestments/{pan}/{fundId}")
	public ResponseEntity<List<Transaction>> getTransactionsByFundId(@PathVariable String pan,
			@PathVariable long fundId) {
		List<Transaction> transactions = getTransactionsByPan(pan).getBody();
		List<Transaction> transaction2 = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if (transaction.getMutualFundId() == fundId) {
				transaction2.add(transaction);
			}
		}
		return new ResponseEntity<List<Transaction>>(transaction2, HttpStatus.OK);

	}
}