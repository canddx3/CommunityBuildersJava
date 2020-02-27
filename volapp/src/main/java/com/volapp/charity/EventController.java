package com.volapp.charity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	private MySQLUserDetailsService userService;
	
	@GetMapping("/event")
	public List<Event> getEvents(){
		List<Event> foundEvents = eventRepository.findAll();
		return foundEvents;
	}
	
	@GetMapping("/event/{username}")
	public ResponseEntity<List<Event>> getUserEvents(@PathVariable(value="username") String username) throws Exception{
		try {
		
			List<Event> foundEvents = eventRepository.findByUsername(username);
			
			return ResponseEntity.ok(foundEvents);
			
		} catch (Exception ex) {
			
			throw new Exception("Could not locate any events for this organization." + ex);
		}
		
		/*
		 * if(foundEvents == null) { return ResponseEntity.notFound().header("Message",
		 * "No event found with that eventID").build(); } return
		 * ResponseEntity.ok(foundEvents);
		 */
	}
	
	@PostMapping("/event/{username}")
	public ResponseEntity<Event> createEvent(@RequestParam("eventID") Long eventID, @RequestParam("eventName") String eventName, @RequestParam("eventDescription") String eventDescription, @RequestParam("eventDay") String eventDay, @RequestParam("eventMonth") String eventMonth, @RequestParam("eventYear") String eventYear, Model model) {
		Event foundEvent = eventRepository.findByEventID(eventID);
		if(foundEvent == null) {
			Event newEvent = new Event();
			newEvent.getEventID();
			newEvent.setEventName(eventName);
			newEvent.setEventDescription(eventDescription);
			newEvent.setEventDay(eventDay);
			newEvent.setEventMonth(eventMonth);
			newEvent.setEventYear(eventYear);
			userService.Save(newEvent);
			return ResponseEntity.ok(newEvent);
		} else {
    		model.addAttribute("exists", true);
    		return ResponseEntity.ok(foundEvent);
    	}
		
	}
	
	@PutMapping("/event/{username}/{eventID}")
	public ResponseEntity<Event> updateEvent(@PathVariable(value="eventID") Long eventID, @RequestBody Event event) throws Exception {
		// Saving to DB using an instance of the repo interface.
		try {
			
			Event foundEvent = eventRepository.findByEventID(eventID);
			// RespEntity crafts response to include correct status codes.
			if(foundEvent == null) {
				return ResponseEntity.notFound().header("Message",  "No event found with that eventID").build();
			}
			
			else {
				foundEvent.getEventID();
				foundEvent.setEventName(event.getEventName());
				foundEvent.setEventDescription(event.getEventDay());
				foundEvent.setEventDay(event.getEventDay());
				foundEvent.setEventMonth(event.getEventMonth());
				foundEvent.setEventYear(event.getEventYear());
				eventRepository.Save(foundEvent);
			}
			
			return ResponseEntity.ok(foundEvent);			
		} catch (Exception ex) {
			
			throw new Exception("Could not locate any event for this organization with that eventID." + ex);
		}
	}
	
	@DeleteMapping("/event/{username}/{eventID}")
	public ResponseEntity<Event> deleteUser(@PathVariable(value="eventID") Long eventID) {
		Event foundEvent = eventRepository.findByEventID(eventID).orElse(null);
		
		if(foundEvent == null) {
			return ResponseEntity.notFound().header("Message",  "No account with that username").build();
		}
		else {
			eventRepository.delete(foundEvent);
		}
		return ResponseEntity.ok().build();
	}
}
