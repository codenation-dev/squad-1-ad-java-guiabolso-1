package br.com.guiabolso.centraldeerros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.repositories.AccountRepository;

@Service
public class AccountService {
	
	@Autowired(required=false)
	private AccountRepository accountRepository;
	
	public AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	public Iterable<Account> list() {
		return accountRepository.findAll();
	}
	
	public Iterable<Account> save(List<Account> accounts) {
		return accountRepository.saveAll(accounts);
	}
}