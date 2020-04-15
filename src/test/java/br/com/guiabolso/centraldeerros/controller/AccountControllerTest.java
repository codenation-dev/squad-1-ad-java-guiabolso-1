package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.dto.AccountDTO;
import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.mapper.AccountMapper;
import br.com.guiabolso.centraldeerros.mocks.EventsMocks;
import br.com.guiabolso.centraldeerros.secutiry.TokenAuthenticationService;
import br.com.guiabolso.centraldeerros.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static br.com.guiabolso.centraldeerros.mocks.EventsMocks.asJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({AccountController.class, AccountMapper.class})
@ContextConfiguration
@WebAppConfiguration
@Import(TokenAuthenticationService.class)
public class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @InjectMocks
    EventsMocks eventsMocks = new EventsMocks();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
         mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    public void shouldReturnAccountById() throws Exception {
        final Account user1 = new Account("Amanda", "123456", "amanda@domain.com");
        final AccountDTO userDTO = createAccountDTO(user1);
        when(accountService.findById(user1.getId())).thenReturn(Optional.of(user1));
        String token = TokenAuthenticationService.create(user1.getUsername());
        this.mockMvc.perform(get("/api/account/0")
                .header("Authorization", "Bearer "+ token))
                .andDo((print()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(asJsonString(userDTO))));
    }

    private AccountDTO createAccountDTO (Account account){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setCreatedAt(account.getCreatedAt());

        return accountDTO;

    }

}