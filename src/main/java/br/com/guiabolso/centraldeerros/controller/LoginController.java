package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.payload.AccountCredentials;
import br.com.guiabolso.centraldeerros.payload.JwtTokenResponse;
import br.com.guiabolso.centraldeerros.secutiry.TokenAuthenticationService;
import br.com.guiabolso.centraldeerros.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class LoginController {
	
    @Autowired
    AccountService accountService;

	@ApiOperation(value = "Método permite o login de usuário já existente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Login feito com sucesso"),
			@ApiResponse(code = 403, message = "Acesso de usuário negador"),
			@ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	}

	)
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

