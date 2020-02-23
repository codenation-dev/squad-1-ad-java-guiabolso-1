package br.com.guiabolso.centraldeerros.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guiabolso.centraldeerros.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
