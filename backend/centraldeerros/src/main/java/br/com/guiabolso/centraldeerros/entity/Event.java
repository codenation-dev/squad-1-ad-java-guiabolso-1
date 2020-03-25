package br.com.guiabolso.centraldeerros.entity;

import br.com.guiabolso.centraldeerros.enums.LevelEnum;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Calendar;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "level", length = 30, nullable = false)
	@NotNull
	@Enumerated(EnumType.STRING)
	private LevelEnum levelEnum;
	
	@Column(name = "log", length = 255, nullable = false)
	@NotNull
	@Size(max = 255)
	private String log;
	
	@Column(name = "description", length = 255, nullable = false)
	@NotNull
	@Size(max = 255)
	private String description;
	
	@Column(name = "origin", length = 100, nullable = false)
	@NotNull
	@Size(max = 100)
	private String origin;

	@Column(name = "environment", length = 100, nullable = false)
	@NotNull
	@Size(max = 100)
	private String environment;

	@Column
	@Min(0L)
	private Long quantity = 0L;

	@Column
	private Boolean archive = false;

	@Column(name = "modified_at", nullable = false)
	@LastModifiedDate
	private LocalDateTime modifiedAt;

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LevelEnum getLevelEnum() {
		return levelEnum;
	}

	public void setLevelEnum(LevelEnum levelEnum) {
		this.levelEnum = levelEnum;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}
}
