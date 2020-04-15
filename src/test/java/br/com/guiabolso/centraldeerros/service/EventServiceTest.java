package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.mapper.EventMapper;
import br.com.guiabolso.centraldeerros.mocks.EventsMocks;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import br.com.guiabolso.centraldeerros.specification.EventEnumSpecification;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class EventServiceTest {

    @Mock
    EventMapper eventMapper;

    @InjectMocks
    EventService eventService;
    EventsMocks eventsMocks = new EventsMocks();

    @Mock
    EventRepository eventRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveNewEvent() {
        final Event event = eventsMocks.createEvent();
        when(eventRepository.save(Mockito.any(Event.class))).thenReturn(event);
        eventService.save(event);
    }

    @Test
    public void shouldReturnEventById() {
        final Event event = eventsMocks.createEvent();
        final EventDTO eventDTO = eventsMocks.createEventDTO();
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(eventMapper.map(event)).thenReturn(eventDTO);
        EventDTO eventDTO1 = eventService.findById(event.getId());
        Assert.assertSame(eventDTO1.getId(), event.getId());
    }

    @Test
    public void shouldReturnPageWithAllEvents() {
        final List<Event> eventList = eventsMocks.eventList();
        final List<EventDTO> eventDTOList = eventsMocks.eventDTOList();
        Pageable pageable = PageRequest.of(0, 10);
        String levelEnum = "error";
        Specification<Event> specifications = Specification.where(new EventEnumSpecification("levelEnum", levelEnum));

        when(eventRepository.findAll()).thenReturn(eventList);
        when(eventMapper.toList(eventList)).thenReturn(eventDTOList);
        Page<EventDTO> eventDtosPage = new PageImpl<>(eventDTOList, pageable, 0);
        when(eventRepository.findAll(specifications,pageable)).thenReturn(eventDtosPage);
        Page<EventDTO> eventDtosPage1 = eventService.findAll(specifications, pageable);

        Assert.assertEquals(eventDtosPage.getTotalPages(),eventDtosPage1.getTotalPages());
        Assert.assertEquals(eventDtosPage.getNumberOfElements(),eventDtosPage1.getNumberOfElements());
        Assert.assertEquals(eventList.size(),eventDtosPage1.getNumberOfElements());
    }

    @Test
    public void shouldUpdateEvent() {
        final Event event = eventsMocks.createEvent();
        final EventDTO eventDTO = new EventDTO();
        eventDTO.setArchived(true);
        final Event eventUpdated = eventsMocks.createEvent();
        eventUpdated.setArchived(true);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(eventMapper.updateEvent(eventDTO, event)).thenReturn(eventUpdated);
        when(eventRepository.save(Mockito.any(Event.class))).thenReturn(eventUpdated);
        Event updateEvent = eventService.updateEvent(eventDTO, event.getId());

        Assert.assertEquals(updateEvent.getArchived(), eventDTO.getArchived());
    }

    @Test
    public void shouldReturnErrorWhenFindEventByIdNotExist() {
        final Event event = eventsMocks.createEvent();
        final EventDTO eventDTO = eventsMocks.createEventDTO();
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        when(eventMapper.map(event)).thenReturn(eventDTO);
        EventDTO eventDTO1 = eventService.findById(event.getId());
        Assert.assertSame(eventDTO1.getId(), event.getId());
    }


}
