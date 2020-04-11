package br.com.guiabolso.centraldeerros.mapper;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public EventDTO map(Event event) {
        if (event == null) return null;

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
        if(event.getModifiedAt() != null) eventDTO.setModifiedAt(event.getModifiedAt());

        return eventDTO;
    }

    @Override
    public List<EventDTO> toList(List<Event> eventList) {
        if (eventList == null) return null;
        return eventList.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Event updateEvent(EventDTO eventDTO, @MappingTarget Event event) {
        if(eventDTO == null) return null;
        if(eventDTO.isArchived()) event.setArchived(true);
        if(eventDTO.getLevelEnum() != null) event.setLevelEnum(eventDTO.getLevelEnum());
        if(eventDTO.getDescription() !=null) event.setDescription(eventDTO.getDescription());
        if(eventDTO.getEnvironment() !=null) event.setEnvironment(eventDTO.getEnvironment());
        if(eventDTO.getOrigin() !=null) event.setOrigin(eventDTO.getOrigin());
        if(eventDTO.getQuantity() !=null) event.setQuantity(eventDTO.getQuantity());
        event.setModifiedAt(eventDTO.modifiedAt());

        return event;
    }
}