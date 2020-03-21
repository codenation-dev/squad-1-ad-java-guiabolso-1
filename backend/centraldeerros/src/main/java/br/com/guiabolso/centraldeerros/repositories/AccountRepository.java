package br.com.guiabolso.centraldeerros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guiabolso.centraldeerros.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
