package br.com.guiabolso.centraldeerros.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.guiabolso.centraldeerros.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {

}