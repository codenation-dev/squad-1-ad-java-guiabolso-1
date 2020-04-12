package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.payload.AccountCredentials;
import br.com.guiabolso.centraldeerros.payload.JwtTokenResponse;
import br.com.guiabolso.centraldeerros.secutiry.TokenAuthenticationService;
import br.com.guiabolso.centraldeerros.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/login")
public class LoginController {
	
    @Autowired
    AccountService accountService;

    @PostMapping(value = "/login", produces = "application/json", consumes = "application/json")
	public ResponseEntity<JwtTokenResponse> login(@Valid @RequestBody AccountCredentials account) {
		try {
			if (accountService.hasAccount(account)) {
				String token = TokenAuthenticationService.create(account.getUsername());
				return new ResponseEntity<>(new JwtTokenResponse(token), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

