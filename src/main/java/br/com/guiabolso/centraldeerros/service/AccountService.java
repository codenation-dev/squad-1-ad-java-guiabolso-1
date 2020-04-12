package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.payload.AccountCredentials;
import br.com.guiabolso.centraldeerros.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(
                "Account not found.", Account.class.getName()));
    }

    public Optional<Account> findByEmail (String email){
    	return accountRepository.findByEmail(email);
	}


    public Account findByUserName (String username){
    	return accountRepository.findByUsername(username).orElseThrow(()-> new ObjectNotFoundException(
                "Account not found.", Account.class.getName()));
	}

	public boolean hasAccount(AccountCredentials account) {
		Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
		if(existingAccount.isPresent()) {
			return passwordEncoder.matches(account.getPassword(), existingAccount.get().getPassword());
		}
		return false;
	}

}
