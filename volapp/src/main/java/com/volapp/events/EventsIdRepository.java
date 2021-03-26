package com.volapp.events;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsIdRepository extends JpaRepository<Events, String>{
	Events findById(Long id);
}
