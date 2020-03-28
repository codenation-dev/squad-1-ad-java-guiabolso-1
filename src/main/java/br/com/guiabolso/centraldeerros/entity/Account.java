package br.com.guiabolso.centraldeerros.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)

public class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "username", length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
	private String username;
	
	@Column(name = "password", length = 100, nullable = false)
    @NotNull
    @Size(max = 100)
	private String password;
	
	@Column(name = "email", length = 100, nullable = false)
	@Email
    @NotNull
    @Size(max = 100)
	private String email;

	@Column(name = "created_at")
	@CreatedDate
	private LocalDateTime createdAt;

	public Account(@NotNull @Size(max = 100) String username, @NotNull @Size(max = 100) String password, @Email @NotNull @Size(max = 100) String email, LocalDateTime createdAt) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.createdAt = createdAt;
	}

	public Account() {
	}

}
