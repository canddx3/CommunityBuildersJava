package com.volapp.events;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.volapp.events.Events;


public interface EventsRepository extends JpaRepository<Events, String>{

	Events findById(Integer id);
	
	List<Events> findByCharityName(String charityName);
	
}
