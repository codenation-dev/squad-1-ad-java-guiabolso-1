//package br.com.guiabolso.centraldeerros.controller;
//
//import br.com.guiabolso.centraldeerros.dto.EventDTO;
//import br.com.guiabolso.centraldeerros.entity.Event;
//import br.com.guiabolso.centraldeerros.enums.LevelEnum;
//import br.com.guiabolso.centraldeerros.mapper.EventMapper;
//import br.com.guiabolso.centraldeerros.mocks.EventsMocks;
//import br.com.guiabolso.centraldeerros.repositories.EventRepository;
//import br.com.guiabolso.centraldeerros.service.EventService;
//import lombok.AllArgsConstructor;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import static org.junit.Assert.assertEquals;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WithMockUser(username = "user1", password = "pwd", roles = "USER")
//public class EventControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    EventMapper eventMapper;
//
//    @InjectMocks
//    EventService eventService;
//    EventsMocks eventsMocks = new EventsMocks();
//
//    @BeforeEach
//    public void setup(){
//        MockitoAnnotations.initMocks(this);
//    }
//
//
//    @Test
//    public void shouldReturnNewEvent() throws Exception {
//        final Event event = eventsMocks.createEvent();
//        final EventDTO eventDTO = eventsMocks.createEventDTO();
//        Mockito.when(eventService.save(Mockito.any(Event.class))).thenReturn(event);
//        Mockito.when(eventMapper.map(event)).thenReturn(eventDTO);
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/api/event")
//                .content(eventDTO.toString())
//                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//        MockHttpServletResponse response = result.getResponse();
//        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
//    }
//
//}
