package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.dto.AccountDTO;
import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.mapper.AccountMapper;
import br.com.guiabolso.centraldeerros.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
	
    @Autowired
    AccountService accountService;
    private AccountMapper accountMapper;

    @ApiOperation(value = "Método cria um novo usuário")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Novo usuário criado como sucesso"),
			@ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	}

	)
    @PostMapping(produces = "application/json")
    public ResponseEntity<AccountDTO> saveAccount(@Valid @RequestBody Account account){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(accountMapper.map(accountService.save(account)));
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@ApiOperation(value = "Método retorna um usuário localizado pelo ID")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Usuário localizado com sucesso pelo ID"),
			@ApiResponse(code = 404, message = "Usuário não localizado pelo ID"),
			@ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	}

	)
    @GetMapping("/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable(value = "id") Long id) {
		try {
			Optional<Account> account = accountService.findById(id);
			if (account.isPresent()) {
				return new ResponseEntity<AccountDTO>(accountMapper.map(account.get()), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Método atualoza um usuário já existente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Usuário localizado e atualizado"),
			@ApiResponse(code = 404, message = "Usuário não localizado pelo ID"),
			@ApiResponse(code = 500, message = "O servidor encontrou um erro não previsto")
	}

	)
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

