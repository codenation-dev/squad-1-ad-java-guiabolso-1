package br.com.guiabolso.centraldeerros;

import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.enums.LevelEnum;
import br.com.guiabolso.centraldeerros.service.AccountService;
import br.com.guiabolso.centraldeerros.service.EventService;

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
	public CommandLineRunner newAccount(AccountService accountService) throws Exception {
		return (String[] args) -> {
			if(accountService.isEmpty()) {
				Account user = new Account();
	
				user.setUsername("admin");
				user.setPassword("admin");
				user.setEmail("admin@email.com");
	
				accountService.save(user);
			}
		};
	}

	@Bean
	public CommandLineRunner commandLineRunner(EventService eventService) throws Exception {
		return (String[] args) -> {
			Event event = new Event();
			event.setLevelEnum(LevelEnum.find("warning"));
			event.setDescription("user.SErvice.Auth: password.Password.Compare: crypto/bcrypt");
			event.setEnvironment("production");
			event.setLog("Error log description");
			event.setOrigin("127.0.0.1");

			eventService.save(event);
		};
	}
}
