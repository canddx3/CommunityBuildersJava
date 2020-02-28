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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.volapp.charity.Volunteer;

@RestController
public class VolunteerController {

	@Autowired
	volunteerRepository volunteerRepository;
	
	@Autowired
	private CharityImageStorageService charityImageStorageService;
	
	@Autowired
	private MySQLUserDetailsService userService;
	
	@RequestMapping({"/", "/home"})
	public String home() {
		return "home.html";
	}
	
	@RequestMapping("/Volunteer")
	public String Volunteer() {
		return "Volunteer.html";
	}
	
	@RequestMapping("/Login")
	public String Login() {
		return "Login.html";
	}
	
	@GetMapping("/Volunteer")
	public List<Volunteer> getVolunteers(){
		List<Volunteer> foundRepos = volunteerRepository.findAll();
		return foundRepos;
	}
	
	@GetMapping("/Volunteer/{volunteerUsername}")
	public ResponseEntity<Volunteer> getCharity(@PathVariable(value="volunteerUsername") String volunteerUsername) {
		Volunteer foundVolunteer = volunteerRepository.findByVolunteerUsername(volunteerUsername).orElse(null);
		
		if(foundVolunteer == null) {
			return ResponseEntity.notFound().header("Message",  "No account found with that volunteerUsername").build();
		}
		return ResponseEntity.ok(foundVolunteer);
	}
	
    @PostMapping("/Volunteer/{volunteerUsername}")
    public ResponseEntity<Volunteer> createVolunteer(@RequestParam("charityPhone") Long charityPhone ,@RequestParam("charityZip") Long charityZip ,@RequestParam("charityState") String charityState ,@RequestParam("charityCity") String charityCity ,@RequestParam("charityStreet") String charityStreet ,@RequestParam("charityCat") String charityCat ,@RequestParam("charityName") String charityName ,@RequestParam("charityTitle") String charityTitle ,@RequestParam("volunteerUsername") String volunteerUsername, @RequestParam("password") String password, @RequestParam("fileName") String fileName, @RequestParam("fileType") String fileType, @RequestParam("data") byte[] data, Model model) {
    	Volunteer foundVolunteer = volunteerRepository.findByvolunteerUsername(volunteerUsername);
    	if (foundVolunteer == null) {
    		Volunteer newVolunteer = new Volunteer();
    		newVolunteer.setCharityTitle(charityTitle);
    		newVolunteer.setCharityName(charityName);
    		newVolunteer.setCharityCat(charityCat);
    		newVolunteer.setCharityStreet(charityStreet);
    		newVolunteer.setCharityCity(charityCity);
    		newVolunteer.setCharityState(charityState);
    		newVolunteer.setCharityZip(charityZip);
    		newVolunteer.setCharityPhone(charityPhone);
    		newVolunteer.setvolunteerUsername(volunteerUsername);
    		newVolunteer.setPassword(password);
    		newVolunteer.setFileName(fileName);
    		newVolunteer.setFileType(fileType);
    		newVolunteer.setData(data);
    		VolunteerService.Save(newVolunteer);
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
		Volunteer foundVolunteer = volunteerRepository.findByvolunteerUsername(volunteerUsername).orElse(null);
		
		// RespEntity crafts response to include correct status codes.
		if(foundVolunteer == null) {
			return ResponseEntity.notFound().header("Message",  "No account found with that volunteerUsername").build();
		}
		else {
			foundVolunteer.setvolunteerUsername(Volunteer.getvolunteerUsername());
			foundVolunteer.setPassword(Volunteer.getPassword());
			foundVolunteer.setCharityCat(Volunteer.getCharityCat());
			foundVolunteer.setCharityTitle(Volunteer.getCharityTitle());
			foundVolunteer.setCharityName(Volunteer.getCharityName());
			foundVolunteer.setCharityStreet(Volunteer.getCharityStreet());
			foundVolunteer.setCharityCity(Volunteer.getCharityCity());
			foundVolunteer.setCharityState(Volunteer.getCharityState());
			foundVolunteer.setCharityZip(Volunteer.getCharityZip());
			foundVolunteer.setCharityPhone(Volunteer.getCharityPhone());
			foundVolunteer.setAboutUs(Volunteer.getAboutUs());
			foundVolunteer.setFileName(Volunteer.getFileName());
			foundVolunteer.setFileType(Volunteer.getFileType());
			foundVolunteer.setData(Volunteer.getData());
			volunteerRepository.save(foundVolunteer);
		}
		return ResponseEntity.ok(foundVolunteer);
	}
    
    @DeleteMapping("/Volunteer/{volunteerUsername}")
	public ResponseEntity<Volunteer> deleteVolunteer(@PathVariable(value="volunteerUsername") String volunteerUsername) {
		Volunteer foundVolunteer = volunteerRepository.findByvolunteerUsername(volunteerUsername).orElse(null);
		
		if(foundVolunteer == null) {
			return ResponseEntity.notFound().header("Message",  "No account with that volunteerUsername").build();
		}
		else {
			volunteerRepository.delete(foundVolunteer);
		}
		return ResponseEntity.ok().build();
	}
}