package br.com.guiabolso.centraldeerros;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import br.com.guiabolso.centraldeerros.repositories.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.guiabolso.centraldeerros.service.AccountService;
import br.com.guiabolso.centraldeerros.service.EventService;
import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.entity.Event;

@SpringBootApplication
public class CentraldeerrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentraldeerrosApplication.class, args);

		System.out.println("\naplicação rodando ============== banco conectado");
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountRepository accountRepository) throws Exception {
		return (String[] args) -> {
			Account user1 = new Account();
			Account user2 = new Account();

			user1.setUsername("Amanda");
			user1.setPassword("123456");
			user1.setEmail("amanda@domain.com");

			user2.setUsername("Eve");
			user2.setPassword("password");
			user2.setEmail("evee@domain.com");

			accountRepository.save(user1);
			accountRepository.save(user2);
		};
	}
}
