package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    public String getCurrentUsername()
    {
    	Object accountDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
    	if (accountDetails instanceof UserDetails)
    	{
    		return ((UserDetails)accountDetails).getUsername();
    	}
    	return null;
    }
    
    public void doLogin()
    {
    	
    }
    
    public UserDetails findByUsername(String username) {
    	
        Account account = accountRepository.findByUsername(username);
        if (account == null) throw new UsernameNotFoundException(username);

        return new org.springframework.security.core.userdetails.User(account.getUsername(), account.getPassword(), null);
    }
  
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }



    public void save(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }
    
    public void doLogin(String username, String password)
    {
    	UserDetails userDetails = (UserDetails) accountRepository.findByUsername(username);
    	UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, password);
    	authenticationManager.authenticate(upat);
    	
    	if (upat.isAuthenticated())
    	{
    		SecurityContextHolder.getContext().setAuthentication(upat);
    		logger.debug(String.format("Usuario logado com sucesso.", username));
    	}
    }
    
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public Optional<Account> findByEmail (String email){
    	return accountRepository.findByEmail(email);
	}
}