package com.cts.bnym.respository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.bnym.entity.Account;

@Repository
public interface BankAccountRepository extends CrudRepository<Account, String> {

	List<Account> findAllByPan (String pan);

}
