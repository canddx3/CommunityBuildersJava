package com.volapp.volunteer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;*/
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
/*import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.volapp.charity.CharityImageStorageService;*/
import com.volapp.charity.MySQLUserDetailsService;
import com.volapp.volunteer.Volunteer;

@RestController
public class VolunteerController {

	@Autowired
	VolunteerRepository volunteerRepository;
	
	@Autowired
	private MySQLUserDetailsService userService;
	
	@RequestMapping({"/", "/home"})
	public String home() {
		return "home.html";
	}
	
	@RequestMapping("/volunteer")
	public String Volunteer() {
		return "volunteer.html";
	}
	
	@RequestMapping("/volunteer/login")
	public String Login() {
		return "Login.html";
	}
	
	@GetMapping("/volunteer")
	public List<Volunteer> getVolunteers(){
		List<Volunteer> foundVolunteers = volunteerRepository.findAll();
		return foundVolunteers;
	}
	
	@GetMapping("/volunteer/{volunteerUsername}")
	public ResponseEntity<Volunteer> getVolunteer(@PathVariable(value="volunteerUsername") String volunteerUsername) {
		Volunteer foundVolunteer = volunteerRepository.findByVolunteerUsername(volunteerUsername).orElse(null);
		
		if(foundVolunteer == null) {
			return ResponseEntity.notFound().header("Message",  "No volunteer account found with that username").build();
		}
		return ResponseEntity.ok(foundVolunteer);
	}
	
    @PostMapping("/Volunteer/{volunteerUsername}")
    public ResponseEntity<Volunteer> createVolunteer(@RequestParam("volunteerUsername") String volunteerUsername ,@RequestParam("volunteerPassword") String volunteerPassword ,@RequestParam("volunteerEmail") String volunteerEmail ,@RequestParam("volunteerFirstName") String volunteerFirstName ,@RequestParam("volunteerLastName") String volunteerLastName ,@RequestParam("volunteerStreet") String volunteerStreet ,@RequestParam("volunteerCity") String volunteerCity, @RequestParam("volunteerState") String volunteerState, @RequestParam("volunteerZip") String volunteerZip, @RequestParam("volunteerPhone") Long volunteerPhone, Model model) {
    	Volunteer foundVolunteer = volunteerRepository.findByVolunteerUsername(volunteerUsername);
    	if (foundVolunteer == null) {
    		Volunteer newVolunteer = new Volunteer();
    		newVolunteer.setVolunteerUsername(volunteerUsername);
    		newVolunteer.setVolunteerPassword(volunteerPassword);
    		newVolunteer.setVolunteerEmail(volunteerEmail);
    		newVolunteer.setVolunteerFirstName(volunteerFirstName);
    		newVolunteer.setVolunteerLastName(volunteerLastName);
    		newVolunteer.setVolunteerStreet(volunteerStreet);
    		newVolunteer.setVolunteerCity(volunteerCity);
    		newVolunteer.setVolunteerState(volunteerState);
    		newVolunteer.setVolunteerZip(volunteerZip);
    		newVolunteer.setVolunteerPhone(volunteerPhone);
    		userService.Save(newVolunteer);
    		return ResponseEntity.ok(newVolunteer);
    	}
    	else {
    		model.addAttribute("exists", true);
    		return ResponseEntity.ok(foundVolunteer);
    	}
    }
    
	/*
	 * @GetMapping("/Volunteer/{volunteerUsername}/downloadFile/{fileId}") public
	 * ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws
	 * Exception { // Load file from database Volunteer imageFile =
	 * CharityImageStorageService.getFile(fileId);
	 * 
	 * return ResponseEntity.ok()
	 * .contentType(MediaType.parseMediaType(imageFile.getFileType()))
	 * .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +
	 * imageFile.getFileName() + "\"") .body(new
	 * ByteArrayResource(imageFile.getData())); }
	 */
    
	/*
	 * @PostMapping("/Volunteer/{volunteerUsername}/uploadFile") public Response
	 * uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
	 * Volunteer imageFile = charityImageStorageService.storeFile(file);
	 * 
	 * String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	 * .path("/downloadFile/") .path(imageFile.getFileName()) .toUriString();
	 * 
	 * return new Response(imageFile.getFileName(), fileDownloadUri,
	 * file.getContentType(), file.getSize()); }
	 */
    
    @PutMapping("/volunteer/{volunteerUsername}")
	public ResponseEntity<Volunteer> putVolunteer(@PathVariable(value="volunteerUsername") String volunteerUsername, @RequestBody Volunteer Volunteer) {
		// Saving to DB using an instance of the repo interface.
		Volunteer foundVolunteer = volunteerRepository.findByVolunteerUsername(volunteerUsername).orElse(null);
		
		// RespEntity crafts response to include correct status codes.
		if(foundVolunteer == null) {
			return ResponseEntity.notFound().header("Message",  "No volunteer account found with that username").build();
		}
		else {
			foundVolunteer.setVolunteerUsername(Volunteer.getVolunteerUsername());
			foundVolunteer.setVolunteerPassword(Volunteer.getVolunteerPassword());
			foundVolunteer.setVolunteerEmail(Volunteer.getVolunteerEmail());
			foundVolunteer.setVolunteerFirstName(Volunteer.getVolunteerFirstName());
			foundVolunteer.setVolunteerLastName(Volunteer.getVolunteerLastName());
			foundVolunteer.setVolunteerStreet(Volunteer.getVolunteerStreet());
			foundVolunteer.setVolunteerCity(Volunteer.getVolunteerCity());
			foundVolunteer.setVolunteerState(Volunteer.getVolunteerState());
			foundVolunteer.setVolunteerZip(Volunteer.getVolunteerZip());
			foundVolunteer.setVolunteerPhone(Volunteer.getVolunteerPhone());
    		userService.Save(foundVolunteer);
		}
		return ResponseEntity.ok(foundVolunteer);
	}
    
    @DeleteMapping("/Volunteer/{volunteerUsername}")
	public ResponseEntity<Volunteer> deleteVolunteer(@PathVariable(value="volunteerUsername") String volunteerUsername) {
		Volunteer foundVolunteer = volunteerRepository.findByVolunteerUsername(volunteerUsername).orElse(null);
		
		if(foundVolunteer == null) {
			return ResponseEntity.notFound().header("Message",  "No account with that volunteerUsername").build();
		}
		else {
			volunteerRepository.delete(foundVolunteer);
		}
		return ResponseEntity.ok().build();
	}
}