package br.com.guiabolso.centraldeerros.entity;

import br.com.guiabolso.centraldeerros.enums.LevelEnum;

import java.util.Calendar;

import javax.persistence.*;
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
	
	@Column(name = "date", nullable = false)
	@NotNull
	private Calendar date;
	
	@Column(name = "environment", length = 100, nullable = false)
	@NotNull
	@Size(max = 100)
	private String environment;

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

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
}
