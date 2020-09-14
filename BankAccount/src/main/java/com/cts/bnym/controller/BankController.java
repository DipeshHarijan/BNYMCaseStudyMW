package com.cts.bnym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.bnym.entity.Bank;
import com.cts.bnym.service.BankService;

@CrossOrigin
@Controller
@RequestMapping(value = "/bank")
public class BankController {

	@Autowired
	private BankService bankService;

	@PostMapping(value = "/add")
	public ResponseEntity<Bank> addBank(Bank bank) {
		return new ResponseEntity<>(bankService.addBank(bank), HttpStatus.OK);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<Bank>> getBanks() {
		return new ResponseEntity<>(bankService.getBanks(), HttpStatus.OK);
	}

	@GetMapping(value = "/get/{ifscCode}")
	public ResponseEntity<Bank> getBank(@PathVariable String ifscCode) {
		return new ResponseEntity<>(bankService.getBank(ifscCode), HttpStatus.OK);
	}

}
