package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;


    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public AccountDTO findById(Long id) {
        return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }

    public AccountDTO findByEmail (String email){
    	return accountRepository.findByEmail(email).map(AccountDTO::new).orElse(null);
	}
}