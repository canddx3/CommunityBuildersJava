package com.volapp.charity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	Event findByEventID(Long eventID);
	
	List<Event> findByUsername(String username);

}
