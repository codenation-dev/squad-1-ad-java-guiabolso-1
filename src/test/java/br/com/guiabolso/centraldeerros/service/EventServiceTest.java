package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.enums.LevelEnum;
import br.com.guiabolso.centraldeerros.mapper.EventMapper;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import br.com.guiabolso.centraldeerros.specification.EventEnumSpecification;
import br.com.guiabolso.centraldeerros.specification.EventStringSpecification;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventServiceTest {

    @Mock
    EventMapper eventMapper;

    @InjectMocks
    EventService eventService;

    @Mock
    EventRepository eventRepository;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Long id = 1L;
    private LevelEnum level = LevelEnum.valueOf("ERROR");
    private String log = "ArithmeticException";
    private String description = "It is thrown when an exceptional condition has occurred in an arithmetic operation.";
    private String origin = "127.0.0.1";
    private String environment = "development";

    @Test
    public void shouldSaveNewEvent() {
        final Event event = createEvent(id, level, log, description, origin, environment);
        Mockito.when(eventRepository.save(Mockito.any(Event.class))).thenReturn(event);
        eventService.save(event);
    }

    @Test
    public void shouldReturnEventById() {
        final Event event = createEvent(id, level, log, description, origin, environment);
        final EventDTO eventDTO = createEventDTO(id, level, log, description, origin, environment);
        Mockito.when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        Mockito.when(eventMapper.map(event)).thenReturn(eventDTO);
        EventDTO eventDTO1 = eventService.findById(event.getId());
        Assert.assertSame(eventDTO1.getId(), event.getId());
    }

    @Test
    public void shouldReturnPageWithAllEvents() {
        final List<Event> eventList = eventList();
        final List<EventDTO> eventDTOList = eventDTOList();
        Pageable pageable = PageRequest.of(0, 10);
        String levelEnum = "error";
        Specification<Event> specifications = Specification.where(new EventEnumSpecification("levelEnum", levelEnum));

        Mockito.when(eventRepository.findAll()).thenReturn(eventList);
        Mockito.when(eventMapper.toList(eventList)).thenReturn(eventDTOList);
        Page<EventDTO> eventDtosPage = new PageImpl<>(eventDTOList, pageable, 0);
        Mockito.when(eventRepository.findAll(specifications,pageable)).thenReturn(eventDtosPage);
        Page<EventDTO> eventDtosPage1 = eventService.findAll(specifications, pageable);

        Assert.assertEquals(eventDtosPage.getTotalPages(),eventDtosPage1.getTotalPages());
        Assert.assertEquals(eventDtosPage.getNumberOfElements(),eventDtosPage1.getNumberOfElements());
        Assert.assertEquals(eventList.size(),eventDtosPage1.getNumberOfElements());
    }


    private Event createEvent(Long id, LevelEnum levelEnum, String log, String description, String origin, String environment) {
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

    private EventDTO createEventDTO(Long id, LevelEnum levelEnum, String log, String description, String origin, String environment) {
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

    private List<Event> eventList() {
        List<Event> events = new ArrayList<>();
        for (long i = 0; i<=20; i++) {
            Event event = createEvent(1+i, level, log, description, origin, environment);
            events.add(event);
        }
        return events;
    }

    private List<EventDTO> eventDTOList() {
        List<EventDTO> eventDTOList = new ArrayList<>();
        for (long i = 0; i<=20; i++) {
            EventDTO eventDTO = createEventDTO(1+i, level, log, description, origin, environment);
            eventDTOList.add(eventDTO);
        }
        return eventDTOList;
    }

}
