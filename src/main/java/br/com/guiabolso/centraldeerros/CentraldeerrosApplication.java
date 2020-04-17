package br.com.guiabolso.centraldeerros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CentraldeerrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentraldeerrosApplication.class, args);
		System.out.println("\naplicação rodando ============== banco conectado");
	}
}
