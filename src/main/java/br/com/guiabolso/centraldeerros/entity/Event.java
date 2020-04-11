package br.com.guiabolso.centraldeerros.entity;

import br.com.guiabolso.centraldeerros.enums.LevelEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "event")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "level", length = 30, nullable = false)
	@Enumerated(EnumType.STRING)
	private LevelEnum levelEnum;
	
	@Column(name = "log", length = 255, nullable = false)
	@Size(max = 255)
	private String log;
	
	@Column(name = "description", length = 255, nullable = false)
	@Size(max = 255)
	private String description;
	
	@Column(name = "origin", length = 100, nullable = false)
	@Size(max = 100)
	private String origin;

	@Column(name = "environment", length = 100, nullable = false)
	@Size(max = 100)
	private String environment;

	@Column
	@Min(0L)
	private Long quantity = 0L;

	@Column
	private Boolean archive = false;

	@Column(name = "modified_at")
	@LastModifiedDate
	private LocalDateTime modifiedAt;

	@Column(name = "created_at", updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist(){
		this.createdAt = LocalDateTime.now();
	}
	@PreUpdate
	public void preUpdate(){
		this.modifiedAt = LocalDateTime.now();
	}

	public void get() {
	}
}

