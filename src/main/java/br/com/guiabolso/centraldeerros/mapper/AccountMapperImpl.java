package br.com.guiabolso.centraldeerros.mapper;

import br.com.guiabolso.centraldeerros.dto.AccountDTO;
import br.com.guiabolso.centraldeerros.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public AccountDTO map(Account account) {
        if (account == null) return null;

        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setCreatedAt(account.getCreatedAt());

        return accountDTO;
    }
}
