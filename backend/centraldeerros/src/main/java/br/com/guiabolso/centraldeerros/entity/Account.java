package br.com.guiabolso.centraldeerros.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
	
	@Column(name = "modified_at", nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Calendar modifiedAt;
	
	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar createdAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Calendar modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Calendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Calendar createdAt) {
		this.createdAt = createdAt;
	}
}
