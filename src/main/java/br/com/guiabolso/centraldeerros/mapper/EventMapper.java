package br.com.guiabolso.centraldeerros.mapper;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class EventMapper {

    public static EventDTO toEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setLevelEnum(event.getLevelEnum());
        eventDTO.setLog(event.getLog());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setOrigin(event.getOrigin());
        eventDTO.setEnvironment(event.getEnvironment());
        eventDTO.setQuantity(event.getQuantity());
        eventDTO.setCreatedAt(event.getCreatedAt());

        return eventDTO;
    }

    public static Event toEvent(EventDTO eventDTO, Optional<Event> event) {
        event.get().setId(eventDTO.getId());
        event.get().setLevelEnum(eventDTO.getLevelEnum());
        event.get().setLog(eventDTO.getLog());
        event.get().setDescription(eventDTO.getDescription());
        event.get().setOrigin(eventDTO.getOrigin());
        event.get().setEnvironment(eventDTO.getEnvironment());
        event.get().setQuantity(eventDTO.getQuantity());
        event.get().setCreatedAt(eventDTO.getCreatedAt());
        return event.get();
    }

    public static Page<EventDTO> toPageDTO(Page<Event> events) {
        List<EventDTO> eventsDTO = events.stream()
                .map(EventMapper::toEventDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(eventsDTO);
    }

}
