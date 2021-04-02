package com.volapp.events;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/charity/events")
public class EventsController {

	@Autowired
	EventsRepository eventsRepo;
	
	@Autowired
	EventsIdRepository eventsIdRepo;

	@GetMapping
	public List<Events> findAll(){
		return eventsRepo.findAll();
	}

	@PostMapping
	public ResponseEntity<Events> Events(@RequestBody Events events) throws Exception{
		eventsRepo.save(events);
		return ResponseEntity.ok().body(events);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Events> find(@PathVariable("id") Long id){
		Events foundEvent = eventsIdRepo.findById(id);
		if(foundEvent == null) {
			return ResponseEntity.notFound().header("Message", "No event with that eventName").build();
		}
		return ResponseEntity.ok(foundEvent);
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<Events> putEvent(@PathVariable(value="id") Long id, @RequestBody Events events) throws Exception{
		Events foundEvent= eventsIdRepo.findById(id);
		
		if(foundEvent == null) {
			return ResponseEntity.notFound().header("Message", "Event not found").build();
			}
		else {
			foundEvent.setCharityName(events.getCharityName());
			foundEvent.setEventName(events.getEventName());
			foundEvent.setEventLocation(events.getEventLocation());
			foundEvent.setEventDate(events.getEventDate());
			foundEvent.setEventTime(events.getEventTime());
			foundEvent.setEventDescription(events.getEventDescription());
			eventsIdRepo.save(foundEvent);
		}
		return ResponseEntity.ok(foundEvent);
		
	}
    @DeleteMapping("/{id}")
	public ResponseEntity<Events> deleteEvent(@PathVariable(value="id") Long id) {
		Events foundEvent = eventsIdRepo.findById(id);
		
		if(foundEvent == null) {
			return ResponseEntity.notFound().header("Message",  "Event not found").build();
		}
		else {
			eventsIdRepo.delete(foundEvent);
		}
		return ResponseEntity.ok().build();
	}
}
