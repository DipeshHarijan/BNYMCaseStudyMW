package com.cts.bnym.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.bnym.entity.Account;
import com.cts.bnym.entity.Bank;
import com.cts.bnym.model.Customer;
import com.cts.bnym.respository.BankAccountRepository;

@Service
public class BankAccountService {

	@Autowired
	BankAccountRepository repo;

	@Autowired
	private BankService bankService;

	@Autowired
	CustomerProxy cs;

	public Account addAccount(Account account) {
		Customer customer = cs.getCustomer(account.getPan()).getBody();
		List<Account> accounts = getByPan(account.getPan());
		Bank bank = bankService.getBank(account.getBank().getIfscCode());
		Account ac = getAccount(account.getAccountNumber());
		System.out.println(accounts.size());
		System.out.println(account);
		System.out.println(customer);
		if (customer != null && accounts.size() < 4 && bank != null && ac == null) {
			return repo.save(account);
		}
		return null;
	}

	public List<Account> getByPan(String pan) {
		return repo.findAllByPan(pan);
	}

	public List<Account> getAllAccounts() {
		return (List<Account>) repo.findAll();
	}

	public String deleteAccount(Long accountNumber) {
		repo.deleteById(accountNumber);
		return "Account deleted";
	}

	public Account getAccount(Long accountNumber) {
		return repo.findById(accountNumber).orElse(null);
	}

}
