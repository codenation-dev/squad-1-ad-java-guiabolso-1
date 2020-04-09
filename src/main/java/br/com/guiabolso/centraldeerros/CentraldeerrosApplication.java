package br.com.guiabolso.centraldeerros;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.enums.LevelEnum;
import br.com.guiabolso.centraldeerros.repositories.AccountRepository;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CentraldeerrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentraldeerrosApplication.class, args);

		System.out.println("\naplicação rodando ============== banco conectado");
	}

	@Bean
	public CommandLineRunner newAccount(AccountRepository accountRepository) throws Exception {
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

	@Bean
	public CommandLineRunner commandLineRunner(EventRepository eventRepository) throws Exception {
		return (String[] args) -> {
			Event event = new Event();
			event.setLevelEnum(LevelEnum.find("warning"));
			event.setDescription("user.SErvice.Auth: password.Password.Compare: crypto/bcrypt");
			event.setEnvironment("Produção");
			event.setLog("Error log description");
			event.setOrigin("127.0.0.1");

			eventRepository.save(event);
		};
	}
}
