package br.com.guiabolso.centraldeerros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CentraldeerrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentraldeerrosApplication.class, args);

		System.out.println("\naplicação rodando ============== banco conectado");
	}

}
