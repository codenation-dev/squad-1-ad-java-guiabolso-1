package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.repositories.AccountRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;


public class AccountServiceTest {
	
	@Mock
	private PasswordEncoder passwordEncoder;

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnAccountById() {
        Account user1 = new Account("Amanda","123456", "amanda@domain.com");
        Mockito.<Optional<Account>>when(accountRepository.findById(1L)).thenReturn(Optional.of(user1));
        Account account = accountService.findById(1L);
        Assert.assertEquals("Amanda", user1.getUsername());
    }

    @Test
    public void shouldSaveNewAccount(){
        final Account user1 = new Account( "Amanda","123456", "amanda@domain.com");
        Mockito.when(passwordEncoder.encode(user1.getPassword())).thenReturn("Encoded password");
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(user1);
        accountService.save(user1);

    }
    @Test
    public void shouldReturnAccountByEmail(){
        Account user1 = new Account("Amanda","123456", "amanda@domain.com");
        Mockito.<Optional<Account>>when(accountRepository.findByEmail("amanda@domain.com")).thenReturn(Optional.of(user1));
        Optional<Account> account = accountService.findByEmail("amanda@domain.com");
        Assert.assertTrue(account.isPresent());
        Assert.assertEquals("Amanda",user1.getUsername());
    }

}
