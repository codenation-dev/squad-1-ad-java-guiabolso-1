package br.com.guiabolso.centraldeerros.mapper;

import br.com.guiabolso.centraldeerros.dto.EventDTO;
import br.com.guiabolso.centraldeerros.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
        @Mappings({
                @Mapping(source = "id", target = "id"),
                @Mapping(source = "levelEnum", target = "levelEnum"),
                @Mapping(source = "log", target = "log"),
                @Mapping(source = "description", target = "description"),
                @Mapping(source = "origin", target = "origin"),
                @Mapping(source = "environment", target = "environment"),
                @Mapping(source = "quantity", target = "quantity"),
                @Mapping(source = "archived", target = "archived"),
                @Mapping(source = "createdAt", target = "createdAt"),
                @Mapping(source = "modifiedAt", target = "modifiedAt")
        })
        EventDTO map(Event event);

        List<EventDTO> toList(List<Event> eventList);

        Event updateEvent(EventDTO eventDTO, @MappingTarget Event event);

    }
