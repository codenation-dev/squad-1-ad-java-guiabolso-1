//package br.com.guiabolso.centraldeerros.controller;
//
//import br.com.guiabolso.centraldeerros.configuration.WebSecurityConfiguration;
//import br.com.guiabolso.centraldeerros.entity.Account;
//import br.com.guiabolso.centraldeerros.repositories.AccountRepository;
//import br.com.guiabolso.centraldeerros.service.AccountService;
//import lombok.AllArgsConstructor;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.junit.Assert.assertEquals;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Import(WebSecurityConfiguration.class)
//@AutoConfigureMockMvc
//@WebAppConfiguration
//@SpringBootTest
//public class AccountControllerTest {
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    AccountRepository accountRepository;
//
//
//    @InjectMocks
//    AccountService accountService;
//
//    @Autowired
//    private AccountController accountController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(accountController)
//                .build();
//    }
//
//
//    @Test
//    public void shouldSaveNewAccount() throws Exception {
//        final Account user1 = new Account("Amanda", "123456", "amanda@domain.com");
//        Mockito.when(passwordEncoder.encode(user1.getPassword())).thenReturn("Encoded password");
//        user1.setPassword("passwordEncoded");
//        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(user1);
//        Mockito.when(accountService.save(Mockito.any(Account.class))).thenReturn(user1);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/account").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
////        RequestBuilder requestBuilder = MockMvcRequestBuilders
////                .post("/api/account")
////                .content(user1.toString())
////                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
////        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
////
////        MockHttpServletResponse response = result.getResponse();
////        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//    }
//}