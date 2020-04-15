package br.com.guiabolso.centraldeerros.controller;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Account;
import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.enums.LevelEnum;
import br.com.guiabolso.centraldeerros.mapper.EventMapper;
import br.com.guiabolso.centraldeerros.mocks.EventsMocks;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import br.com.guiabolso.centraldeerros.secutiry.TokenAuthenticationService;
import br.com.guiabolso.centraldeerros.service.EventService;
import br.com.guiabolso.centraldeerros.specification.EventEnumSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static br.com.guiabolso.centraldeerros.mocks.EventsMocks.asJsonString;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({EventsController.class, EventMapper.class})
@ContextConfiguration
@AutoConfigureMockMvc
@WebAppConfiguration
@Import(TokenAuthenticationService.class)
public class EventControllerTest {

    @MockBean
    private EventService eventService;

    @InjectMocks
    EventsMocks eventsMocks = new EventsMocks();


    @Mock
    EventMapper eventMapper;

    @Mock
    EventRepository eventRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    public void shouldCreateNewEvent() throws Exception {
        final Account user1 = new Account("Amanda", "123456", "amanda@domain.com");
        final Event event = eventsMocks.createEvent();
        final EventDTO eventDTO = eventsMocks.createEventDTO();
        String token = TokenAuthenticationService.create(user1.getUsername());
        when(eventService.save(any(Event.class))).thenReturn(event);
        mockMvc.perform(post("/api/event")
                .header("Authorization", "Bearer "+ token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(eventDTO))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(asJsonString(eventDTO))));

    }

    @Test
    @WithMockUser
    public void shouldReturnEventById() throws Exception {
        final Account user1 = new Account("Amanda", "123456", "amanda@domain.com");
        final Event event = eventsMocks.createEvent();
        final EventDTO eventDTO = eventsMocks.createEventDTO();
        String token = TokenAuthenticationService.create(user1.getUsername());
        when(eventService.findById(event.getId())).thenReturn(eventDTO);
        mockMvc.perform(get("/api/event/1")
                .header("Authorization", "Bearer "+ token))
                .andDo((print()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(asJsonString(eventDTO))));

    }

    @Test
    @WithMockUser
    public void shouldReturnEventsByPageAndFilters() throws Exception {
        final Account user1 = new Account("Amanda", "123456", "amanda@domain.com");
        final List<EventDTO> eventsDTOs = new ArrayList<>();
        EventDTO eventDebugDTO = new EventDTO(true);
        EventDTO eventDebugDTO2 = new EventDTO(true);
        eventsDTOs.add(eventDebugDTO);
        eventsDTOs.add(eventDebugDTO2);

        String archived = "true";
        Specification<Event> specifications = Specification.where(new EventEnumSpecification("archived", archived));

        Pageable pageable = PageRequest.of(0, 10);
        Page<EventDTO> eventDtosPage = new PageImpl<>(eventsDTOs, pageable, eventsDTOs.size());
        String token = TokenAuthenticationService.create(user1.getUsername());

        when(eventService.findAll(eq(specifications), any(Pageable.class))).thenReturn(eventDtosPage);
        mockMvc.perform(get("/api/event?size=10&archived=true")
                .header("Authorization", "Bearer "+ token))
                .andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void shouldDeleteEvent() throws Exception {
        final Account user1 = new Account("Amanda", "123456", "amanda@domain.com");
        final Event event = eventsMocks.createEvent();
        String token = TokenAuthenticationService.create(user1.getUsername());
        mockMvc.perform(delete("/api/event/1")
                .header("Authorization", "Bearer "+ token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    public void shouldUpdateEvent() throws Exception {
        final Account user1 = new Account("Amanda", "123456", "amanda@domain.com");
        final Event event1 = eventsMocks.createEvent();
        event1.setArchived(true);
        final EventDTO eventDTO = new EventDTO(true);
        final EventDTO eventDTOUpdated = eventsMocks.createEventDTO();
        eventDTOUpdated.setArchived(true);
        String token = TokenAuthenticationService.create(user1.getUsername());
        when(eventService.updateEvent(any(EventDTO.class), eq(1L))).thenReturn(event1);
        mockMvc.perform(patch("/api/event/1")
                .header("Authorization", "Bearer "+ token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(eventDTO))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().string(containsString(asJsonString(eventDTOUpdated))))
                .andDo(MockMvcResultHandlers.print());

    }

}
