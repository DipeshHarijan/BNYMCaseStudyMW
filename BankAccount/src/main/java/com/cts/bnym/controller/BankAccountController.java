package com.cts.bnym.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping(value = "/account")
public class BankAccountController {

	@Autowired
	private BankAccountService service;

	@HystrixCommand(fallbackMethod = "addAccountFailed")
	@PostMapping(value = "/add")
	public ResponseEntity<String> addAccount(@Valid @RequestBody Account account) {
		Account acc = service.addAccount(account);
		if (acc != null) {
			return new ResponseEntity<String>("Account created", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Account creation failed. A customer can have at most 4 bank accounts.",
				HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addAccountFailed(@Valid @RequestBody Account account) {
		return new ResponseEntity<String>("Server unavailable. Account not created.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Account>> getAllAccounts() {
		return new ResponseEntity<List<Account>>(service.getAllAccounts(), HttpStatus.OK);
	}

	@GetMapping(value = "/get/{pan}")
	public ResponseEntity<List<Account>> getByPan(@PathVariable String pan) {
		return new ResponseEntity<List<Account>>(service.getByPan(pan), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{accountNumber}")
	public ResponseEntity<String> deleteAccount(@PathVariable String accountNumber) {
		if (service.getAccount(accountNumber) != null)
			return new ResponseEntity<String>(service.deleteAccount(accountNumber), HttpStatus.OK);
		return new ResponseEntity<String>("Account not found.", HttpStatus.BAD_REQUEST);
	}

	@PutMapping(value = "/update")
	public ResponseEntity<String> updateAccount(@RequestBody Account account) {
		if (service.getAccount(account.getAccountNumber()) != null) {
			service.addAccount(account);
			return new ResponseEntity<String>("Account updated", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<String>("Account not found", HttpStatus.BAD_REQUEST);
	}
}
