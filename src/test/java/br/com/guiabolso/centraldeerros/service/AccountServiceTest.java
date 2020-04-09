//package br.com.guiabolso.centraldeerros.service;
//
//import br.com.guiabolso.centraldeerros.entity.Account;
//import br.com.guiabolso.centraldeerros.repositories.AccountRepository;
//import org.junit.Assert;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//
//public class AccountServiceTest {
//
//    @InjectMocks
//    AccountService accountService;
//
//    @Mock
//    AccountRepository accountRepository;
//
//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void shouldReturnAccountById() {
//        Account user1 = new Account("Amanda","123456", "amanda@domain.com", LocalDateTime.now());
//        Mockito.<Optional<Account>>when(accountRepository.findById(1L)).thenReturn(Optional.of(user1));
//        Optional<Account> account = accountService.findById(1L);
//        Assert.assertTrue(account.isPresent());
//        Assert.assertEquals("Amanda", user1.getUsername());
//    }
//
//    @Test
//    public void shouldSaveNewAccount(){
//        final Account user1 = new Account( "Amanda","123456", "amanda@domain.com", LocalDateTime.now());
//        accountService.saveAccount(user1);
//
//    }
//    @Test
//    public void shouldReturnAccountByEmail(){
//        Account user1 = new Account("Amanda","123456", "amanda@domain.com", LocalDateTime.now());
//        Mockito.<Optional<Account>>when(accountRepository.findByEmail("amanda@domain.com")).thenReturn(Optional.of(user1));
//        Optional<Account> account = accountService.findByEmail("amanda@domain.com");
//        Assert.assertTrue(account.isPresent());
//        Assert.assertEquals("Amanda",user1.getUsername());
//    }
//
//}
