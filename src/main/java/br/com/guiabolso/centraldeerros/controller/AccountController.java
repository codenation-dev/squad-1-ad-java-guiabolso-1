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
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping(produces = "application/json")
    public ResponseEntity<Account> saveAccount(@Valid @RequestBody Account account) {
        try {
            return new ResponseEntity<Account>(accountService.save(account), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<Account> getAccountByUsername(@PathVariable String username) {
        try {
            return new ResponseEntity<>(accountService.findByUserName(username), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Account> updateAccount(@Valid @RequestBody Account accountUpdated, @PathVariable Long id) {
        try {
            Account account = accountService.findById(id);
            accountUpdated.setId(account.getId());
            accountUpdated.setCreatedAt(account.getCreatedAt());
            return new ResponseEntity<>(accountService.save(accountUpdated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

