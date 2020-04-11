package br.com.guiabolso.centraldeerros.mapper;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
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
        eventDTO.setArchived(event.isArchived());
        eventDTO.setCreatedAt(event.getCreatedAt());

        return eventDTO;
    }

    public static Event toEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setId(eventDTO.getId());
        event.setLevelEnum(eventDTO.getLevelEnum());
        event.setLog(eventDTO.getLog());
        event.setDescription(eventDTO.getDescription());
        event.setOrigin(eventDTO.getOrigin());
        event.setEnvironment(eventDTO.getEnvironment());
        event.setQuantity(eventDTO.getQuantity());
        event.setArchived(eventDTO.isArchived());
        event.setCreatedAt(eventDTO.getCreatedAt());
        return event;
    }

    public static List <EventDTO> toPageDTO(List<Event> events) {
        return events.stream()
                .map(EventMapper::toEventDTO)
                .collect(Collectors.toList());
    }
}
