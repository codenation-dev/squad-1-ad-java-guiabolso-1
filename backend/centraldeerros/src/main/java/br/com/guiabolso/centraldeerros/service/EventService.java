package br.com.guiabolso.centraldeerros.service;

import br.com.guiabolso.centraldeerros.entity.Event;
import br.com.guiabolso.centraldeerros.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List <Event> findAllOrderByParams(String params) {
        switch (params) {
            case "level":
                return eventRepository.findAllOrderByLevel();
            case "eventsQuantity":
                return eventRepository.findAllOrderByEventsQuantity();
        }
        return new ArrayList<>();
    }


}
