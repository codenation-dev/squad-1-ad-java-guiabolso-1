package br.com.guiabolso.centraldeerros.repositories;

import br.com.guiabolso.centraldeerros.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findById(Long id);

    @Query("select a from Account a where a.email= ?1")
    Optional<Account> findByEmail(String email);

    Optional<Account> findByUsername(String username);
    
}
