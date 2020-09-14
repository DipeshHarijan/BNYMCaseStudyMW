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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.bnym.entity.MutualFund;
import com.cts.bnym.service.MutualFundService;

@CrossOrigin
@Controller
@RequestMapping(value = "/mutualfund")
public class MutualFundController {

	@Autowired
	private MutualFundService service;

	@PostMapping(value = "/add")
	public ResponseEntity<MutualFund> add(@RequestBody MutualFund fund) {
		MutualFund mFund = service.add(fund);
		if (mFund != null) {
			return new ResponseEntity<>(mFund, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/getall")
	public ResponseEntity<List<MutualFund>> getAll() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/get/{fundId}")
	public ResponseEntity<MutualFund> get(@PathVariable long fundId) {
		return new ResponseEntity<>(service.get(fundId), HttpStatus.ACCEPTED);
	}

}
