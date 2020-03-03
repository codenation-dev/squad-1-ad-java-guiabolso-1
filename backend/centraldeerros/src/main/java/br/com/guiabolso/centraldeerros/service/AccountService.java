package br.com.guiabolso.centraldeerros.service;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account add(Account object) {

        return accountRepository.save(Account) object);

    public Account get(Account id) {

        retunr accountRepository.findById(id);
        }


    }
}
