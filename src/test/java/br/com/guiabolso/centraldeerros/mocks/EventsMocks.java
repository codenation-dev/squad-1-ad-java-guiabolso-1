package br.com.guiabolso.centraldeerros.mocks;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.enums.LevelEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventsMocks {

    private Long id = 1L;
    private LevelEnum levelEnum = LevelEnum.valueOf("ERROR");
    private String log = "ArithmeticException";
    private String description = "It is thrown when an exceptional condition has occurred in an arithmetic operation.";
    private String origin = "127.0.0.1";
    private String environment = "development";

    public Event createEvent() {
        Event event = new Event();
        event.setId(id);
        event.setLevelEnum(levelEnum);
        event.setLog(log);
        event.setDescription(description);
        event.setOrigin(origin);
        event.setEnvironment(environment);
        event.setQuantity(3L);
        return event;
    }

    public EventDTO createEventDTO() {
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(id);
        eventDTO.setLevelEnum(levelEnum);
        eventDTO.setLog(log);
        eventDTO.setDescription(description);
        eventDTO.setOrigin(origin);
        eventDTO.setEnvironment(environment);
        eventDTO.setQuantity(3L);
        return eventDTO;
    }

    public List<Event> eventList() {
        List<Event> events = new ArrayList<>();
        for (long i = 0; i<=20; i++) {
            long l = i + 1;
            Event event = createEvent();
            events.add(event);
        }
        return events;
    }

    public List<EventDTO> eventDTOList() {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (long i = 0; i<=20; i++) {
            long l = i + 1;
            EventDTO eventDTO = createEventDTO();
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
