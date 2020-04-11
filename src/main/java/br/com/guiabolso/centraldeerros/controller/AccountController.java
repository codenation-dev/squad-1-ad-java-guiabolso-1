package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountController {
	
    @Autowired
    AccountService accountService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Account> saveAccount(@Valid @RequestBody Account account){
        try{            
            return new ResponseEntity<Account>(accountService.save(account), HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable(value = "id") Long id) {
		try {
			Optional<Account> account = accountService.findById(id);
			if (account.isPresent()) {
				return new ResponseEntity<Account>(account.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account accountUpdated, @PathVariable(value = "id") Long id) {
		try {
			Optional<Account> account = accountService.findById(id);
			if (account.isPresent()) {
				accountUpdated.setId(account.get().getId());
				accountUpdated.setCreatedAt(account.get().getCreatedAt());
				return new ResponseEntity<Account>(accountService.save(accountUpdated), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

