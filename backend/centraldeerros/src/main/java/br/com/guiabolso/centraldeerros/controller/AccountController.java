package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.dto.AccountDTO;
import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/centralerros/account")
public class AccountController {

    @Autowired
    AccountService accountService;
    AccountDTO accountDTO;

    @PostMapping
    public ResponseEntity<Account> saveAccount(@Valid @RequestBody Account account){
        try{
            this.accountService.saveAccount(account);
            return ResponseEntity.status(HttpStatus.CREATED).body(account);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(accountService.findById(id), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

