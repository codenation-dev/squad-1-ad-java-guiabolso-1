package br.com.guiabolso.centraldeerros.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AccountCredentials implements Serializable {

	private static final long serialVersionUID = 3602548835264984080L;
	
	@NotNull(message = "Please provide an username")
	private String username;
	@NotNull(message = "Please provide a password")
	private String password;
}