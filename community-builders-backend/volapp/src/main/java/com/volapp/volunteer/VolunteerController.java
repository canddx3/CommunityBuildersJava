package com.volapp.volunteer;

//import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.volapp.volunteer.MySQLVolunteerDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/api/charity")
public class VolunteerController {
	

	@Autowired
	VolunteerRepository volunteerRepository;
	
	@Autowired
	private MySQLVolunteerDetailsService userService;
	
	@GetMapping("/volunteer/{username}")
	public UserDetails find(@PathVariable("username") String username) {
		
		return userService.loadUserByUsername(username);
	}
//	@GetMapping("/volunteer/{username}")
//	public ResponseEntity<Volunteer> getVolunteer(@PathVariable(value="username") String username) {
//		Volunteer foundVolunteer = volunteerRepository.findByUsername(username);
//		
//		if(foundVolunteer == null) {
//			return ResponseEntity.notFound().header("Message",  "No volunteer account found with that username").build();
//		}
//		return ResponseEntity.ok(foundVolunteer);
//	}
	
	@PostMapping("/volunteer")
	public ResponseEntity<Volunteer> createVolunteer(@Valid @RequestBody Volunteer volunteer) {
		Volunteer newVolunteer = new Volunteer(volunteer.getId(), volunteer.getUsername(), volunteer.getPassword(), volunteer.getFirstName(), volunteer.getLastName(), volunteer.getEmail(), volunteer.convertPhone(volunteer.getPhone()), volunteer.getStreet(), volunteer.getCity(), volunteer.getState(), volunteer.getZip());
		
		userService.Save(newVolunteer);
		return ResponseEntity.ok().body(volunteer);

	}
	    	  
    @PutMapping("/volunteer/{username}")
	public ResponseEntity<Volunteer> putVolunteer(@PathVariable(value="username") String username, @RequestBody Volunteer Volunteer) {
		// Saving to DB using an instance of the repo interface.
		Volunteer foundVolunteer = volunteerRepository.findByUsername(username).orElse(null);
		
		// RespEntity crafts response to include correct status codes.
		if(foundVolunteer == null) {
			return ResponseEntity.notFound().header("Message",  "No volunteer account found with that username").build();
		}
		else {
			foundVolunteer.setUsername(Volunteer.getUsername());
			foundVolunteer.setPassword(Volunteer.getPassword());
			foundVolunteer.setEmail(Volunteer.getEmail());
			foundVolunteer.setFirstName(Volunteer.getFirstName());
			foundVolunteer.setLastName(Volunteer.getLastName());
			foundVolunteer.setStreet(Volunteer.getStreet());
			foundVolunteer.setCity(Volunteer.getCity());
			foundVolunteer.setState(Volunteer.getState());
			foundVolunteer.setZip(Volunteer.getZip());
			foundVolunteer.setPhone(Volunteer.convertPhone(Volunteer.getPhone()));
    		userService.Save(foundVolunteer);
		}
		return ResponseEntity.ok(foundVolunteer);
	}
    
    @DeleteMapping("/volunteer/{username}")
	public ResponseEntity<Volunteer> deleteVolunteer(@PathVariable(value="username") String username) {
		Volunteer foundVolunteer = volunteerRepository.findByUsername(username).orElse(null);
		
		if(foundVolunteer == null) {
			return ResponseEntity.notFound().header("Message",  "No account with that volunteerUsername").build();
		}
		else {
			volunteerRepository.delete(foundVolunteer);
		}
		return ResponseEntity.ok().build();
	}

}
