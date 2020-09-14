package com.cts.bnym.controller;

import java.util.List;

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

import com.cts.bnym.entity.Account;
import com.cts.bnym.service.BankAccountService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@CrossOrigin
@Controller
@RequestMapping(value = "/account")
public class BankAccountController {

	@Autowired
	private BankAccountService service;

	@HystrixCommand(fallbackMethod = "addAccountFailed")
	@PostMapping(value = "/add")
	public ResponseEntity<Account> addAccount(@RequestBody Account account) {
		long random = (long) (Math.random() * 100000000000L);
		account.setAccountNumber(random);
		Account acc = service.addAccount(account);
		if (acc != null) {
			return new ResponseEntity<>(acc, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Account> addAccountFailed(@RequestBody Account account) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return new ResponseEntity<>(service.getAllAccounts(), HttpStatus.OK);
	}

	@GetMapping(value = "/get/{pan}")
	public ResponseEntity<List<Account>> getByPan(@PathVariable String pan) {
		return new ResponseEntity<>(service.getByPan(pan), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{accountNumber}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber) {
		if (service.getAccount(accountNumber) != null)
			return new ResponseEntity<>(service.deleteAccount(accountNumber), HttpStatus.OK);
		return new ResponseEntity<>("Account not found.", HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
		if (service.getAccount(account.getAccountNumber()) != null) {
			return new ResponseEntity<>(service.addAccount(account), HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
