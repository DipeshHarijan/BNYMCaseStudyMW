package com.cts.bnym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bnym.entity.MutualFund;
import com.cts.bnym.repository.MutualFundRepository;

@Service
public class MutualFundService {

	@Autowired
	private MutualFundRepository repo;

	public MutualFund add(MutualFund fund) {
		return repo.save(fund);
	}

	public List<MutualFund> getAll() {
		return (List<MutualFund>) repo.findAll();
	}

	public MutualFund get(long fundId) {
		MutualFund fund = repo.findById(fundId).orElse(null);
		if(fund!=null) {
			return fund;
		}
		return null;
	}

}
