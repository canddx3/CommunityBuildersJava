package com.volapp.events;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.volapp.volunteer.Volunteer;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	Event findByEventID(Long eventID);
	
	List<Event> findByUsername(String username);

	void save(Volunteer foundVolunteer);

}
