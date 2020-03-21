package br.com.guiabolso.centraldeerros;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
	}
	
	@Bean
	CommandLineRunner runnerAccount(AccountService accountService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/accounts.json");
			try {
				List<Account> accounts = mapper.readValue(inputStream,typeReference);
				accountService.save(accounts);
				System.out.println("Accounts Saved!");
			} catch (IOException e){
				System.out.println("Unable to save accounts: " + e.getMessage());
			}
			
		};
	}
	
	@Bean
	CommandLineRunner runnerEvent(EventService eventService) {
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<Event>> typeReference = new TypeReference<List<Event>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/events.json");
			try {
				List<Event> events = mapper.readValue(inputStream,typeReference);
				eventService.save(events);
				System.out.println("Events Saved!");
			} catch (IOException e){
				System.out.println("Unable to save events: " + e.getMessage());
			}
		};
	}

}
