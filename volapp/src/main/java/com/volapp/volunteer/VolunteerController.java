package com.volapp.volunteer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volapp.charityuser.MySQLUserDetailsService;

@RestController
@RequestMapping("/api/charity")
public class VolunteerController {
	

	@Autowired
	VolunteerRepository volunteerRepository;
	
	@Autowired
	private MySQLUserDetailsService userService;
	
	@GetMapping("/volunteer/{username}")
	public ResponseEntity<Volunteer> getVolunteer(@PathVariable(value="username") String username) {
		Volunteer foundVolunteer = volunteerRepository.findByUsername(username).orElse(null);
		
		if(foundVolunteer == null) {
			return ResponseEntity.notFound().header("Message",  "No volunteer account found with that username").build();
		}
		return ResponseEntity.ok(foundVolunteer);
	}
	
    @PostMapping("/volunteer")
    public ResponseEntity<Volunteer> createVolunteer(@RequestParam("username") String username ,@RequestParam("password") String password ,@RequestParam("email") String email ,@RequestParam("firstName") String firstName ,@RequestParam("lastName") String lastName ,@RequestParam("street") String street ,@RequestParam("city") String city, @RequestParam("state") String state, @RequestParam("zip") String zip, @RequestParam("phone") Long phone, Model model) {
    	Volunteer foundVolunteer = volunteerRepository.findByUsername(username);
    	if (foundVolunteer == null) {
    		Volunteer newVolunteer = new Volunteer();
    		newVolunteer.setUsername(username);
    		newVolunteer.setPassword(password);
    		newVolunteer.setEmail(email);
    		newVolunteer.setFirstName(firstName);
    		newVolunteer.setLastName(lastName);
    		newVolunteer.setStreet(street);
    		newVolunteer.setCity(city);
    		newVolunteer.setState(state);
    		newVolunteer.setZip(zip);
    		newVolunteer.setPhone(phone);
    		userService.Save(newVolunteer);
    		return ResponseEntity.ok(newVolunteer);
    	}
    	else {
    		model.addAttribute("exists", true);
    		return ResponseEntity.ok(foundVolunteer);
    	}
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
			foundVolunteer.setPhone(Volunteer.getPhone());
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
