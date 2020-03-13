package br.com.guiabolso.centraldeerros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guiabolso.centraldeerros.entity.Event;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    List <Event> findAll();

    List <Event> findAllOrderByLevel();

    List <Event> findAllOrderByEventsQuantity();
}
