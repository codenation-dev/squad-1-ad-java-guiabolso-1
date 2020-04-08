package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.payload.AccountCredentials;
import br.com.guiabolso.centraldeerros.secutiry.TokenAuthenticationService;
import br.com.guiabolso.centraldeerros.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class AccountController {
	
    @Autowired
    AccountService accountService;

    @PostMapping(value = "/account", produces = "application/json")
    public ResponseEntity<Account> saveAccount(@Valid @RequestBody Account account){
        try{            
            return new ResponseEntity<Account>(accountService.save(account), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping(value = "/login", produces = "text/plain", consumes = "application/json")
	public ResponseEntity<String> login(@Valid @RequestBody AccountCredentials account) {
		try {
			if (accountService.hasAccount(account)) {
				String token = TokenAuthenticationService.create(account.getUsername());
				return new ResponseEntity<String>(token, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

