package br.com.guiabolso.centraldeerros.dto;

import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.enums.LevelEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;


public class EventsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @Enumerated(EnumType.STRING)
    private LevelEnum levelEnum;
    private String log;
    private String description;
    private String origin;
    private String environment;
    private LocalDateTime createdAt;
    private Long quantity = 0L;
    private Boolean archive = false;

    public EventsDTO(Event event) {
        this.id = event .getId();
        this.levelEnum = event.getLevelEnum();
        this.log = event.getLog();
        this.description = event.getDescription();
        this.origin = event.getOrigin();
        this.environment = event.getEnvironment();
        this.createdAt = event.getCreatedAt();
        this.quantity = event.getQuantity();
        this.archive = event.getArchive();
    }
    public long getId() {
        return id;
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

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }
}

