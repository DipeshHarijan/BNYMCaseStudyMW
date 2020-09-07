package com.cts.bnym.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.bnym.model.MutualFund;

@FeignClient(name = "mutualfund-service")
public interface MutualFundProxy {

	@GetMapping(value = "/mutualfund/getall")
	ResponseEntity<List<MutualFund>> getAll();

	@GetMapping(value = "/mutualfund/get/{fundId}")
	public ResponseEntity<MutualFund> get(@PathVariable long fundId);
}
