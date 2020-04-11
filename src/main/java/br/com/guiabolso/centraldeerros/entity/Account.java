package br.com.guiabolso.centraldeerros.entity;


import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)

public class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "username", length = 100, nullable = false)
	@NotNull(message = "Please provide an username")
    @Size(max = 100)
	private String username;
	
	@Column(name = "password", length = 100, nullable = false)
	@NotNull(message = "Please provide a password")
    @Size(max = 100)
	@JsonIgnore
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	@Column(name = "email", length = 100, nullable = false)
	@Email(message = "E-mail not valid")
	@NotNull(message = "Please provide an e-mail")
    @Size(max = 100)
	private String email;

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	@CreationTimestamp
	private LocalDateTime createdAt;

	public Account(@NotNull @Size(max = 100) String username, @NotNull @Size(max = 100) String password, @Email @NotNull @Size(max = 100) String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Account() {
	}

}
