package br.com.guiabolso.centraldeerros.entity;

import br.com.guiabolso.centraldeerros.enums.LevelEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	
	@Column(name = "level", length = 30, nullable = false)
	@NotNull(message = "Please provide a level")
	@Enumerated(EnumType.STRING)
	private LevelEnum levelEnum;
	
	@Column(name = "log", length = 255, nullable = false)
	@NotNull(message = "Please provide a log")
	@Size(max = 255)
	private String log;
	
	@Column(name = "description", length = 255, nullable = false)
	@NotNull(message = "Please provide a description")
	@Size(max = 255)
	private String description;
	
	@Column(name = "origin", length = 100, nullable = false)
	@NotNull(message = "Please provide an origin")
	@Size(max = 100)
	private String origin;

	@Column(name = "environment", length = 100, nullable = false)
	@NotNull(message = "Please provide an environment")
	@Size(max = 100)
	private String environment;

	@Column
	@Min(value = 0L, message = "Quantity cannot be negative")
	private Long quantity = 0L;

	@Column
	private boolean archived = false;

	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	@Column(name = "created_at", updatable = false)
	@CreatedDate
	@CreationTimestamp
	private LocalDateTime createdAt;

}

