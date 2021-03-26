package com.volapp.events;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volapp.events.Events;

public interface EventsRepository extends JpaRepository<Events, Long>{
	Events findByEventName(String eventName);
	List<Events> findByCharityName(String charityName);
}
