package br.com.guiabolso.centraldeerros.dto;

import br.com.guiabolso.centraldeerros.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {

    private Long id;
    @Enumerated(EnumType.STRING)
    private LevelEnum levelEnum;
    private String log;
    private String description;
    private String origin;
    private String environment;    
    private Long quantity = 0L;
    private Boolean archived;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public LocalDateTime modifiedAt () {
        return LocalDateTime.now();
    }

    public EventDTO(Boolean archived) {
        this.archived = archived;
    }
}
