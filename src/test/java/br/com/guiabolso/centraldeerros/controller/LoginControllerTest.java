package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.mapper.AccountMapper;
import br.com.guiabolso.centraldeerros.mocks.EventsMocks;
import br.com.guiabolso.centraldeerros.payload.AccountCredentials;
import br.com.guiabolso.centraldeerros.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static br.com.guiabolso.centraldeerros.mocks.EventsMocks.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AccountController.class, LoginController.class, AccountMapper.class})
@WebAppConfiguration
public class LoginControllerTest {

    @MockBean
    private AccountService accountService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    EventsMocks eventsMocks = new EventsMocks();

    @Test
    public void shouldReturnSaveNewAccount() throws Exception {
        final Account user1 = new Account("user", "123456", "user@domain.com");
        user1.setPassword("passwordEncoded");
        when(accountService.save(Mockito.any(Account.class))).thenReturn(user1);
        mockMvc.perform(post("/api/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user1))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void shouldReturnToken() throws Exception {
        final Account user1 = new Account("user", "123456", "user@domain.com");
        AccountCredentials login = new AccountCredentials();
        login.setUsername(user1.getUsername());
        login.setPassword(user1.getPassword());
        when(accountService.hasAccount(login)).thenReturn(true);
        mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(login))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").exists());
    }


}
