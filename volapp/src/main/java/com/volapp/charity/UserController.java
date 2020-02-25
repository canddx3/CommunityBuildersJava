package com.volapp.charity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
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

import com.volapp.charity.User;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private CharityImageStorageService charityImageStorageService;
	
	@Autowired
	private MySQLUserDetailsService userService;
	
	@RequestMapping({"/", "/home"})
	public String home() {
		return "home.html";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "user.html";
	}
	
	@RequestMapping("/Login")
	public String Login() {
		return "Login.html";
	}
	
	@GetMapping("/user")
	public List<User> getUsers(){
		List<User> foundRepos = userRepository.findAll();
		return foundRepos;
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getCharity(@PathVariable(value="username") String username) {
		User foundUser = userRepository.findByUsername(username).orElse(null);
		
		if(foundUser == null) {
			return ResponseEntity.notFound().header("Message",  "No account found with that username").build();
		}
		return ResponseEntity.ok(foundUser);
	}
	
    @PostMapping("/user/{username}")
    public ResponseEntity<User> createUser(@RequestParam("charityPhone") Long charityPhone ,@RequestParam("charityZip") Long charityZip ,@RequestParam("charityState") String charityState ,@RequestParam("charityCity") String charityCity ,@RequestParam("charityStreet") String charityStreet ,@RequestParam("charityCat") String charityCat ,@RequestParam("charityName") String charityName ,@RequestParam("charityTitle") String charityTitle ,@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("fileName") String fileName, @RequestParam("fileType") String fileType, @RequestParam("data") byte[] data, Model model) {
    	User foundUser = userRepository.findByUsername(username);
    	if (foundUser == null) {
    		User newUser = new User();
    		newUser.setCharityTitle(charityTitle);
    		newUser.setCharityName(charityName);
    		newUser.setCharityCat(charityCat);
    		newUser.setCharityStreet(charityStreet);
    		newUser.setCharityCity(charityCity);
    		newUser.setCharityState(charityState);
    		newUser.setCharityZip(charityZip);
    		newUser.setCharityPhone(charityPhone);
    		newUser.setUsername(username);
    		newUser.setPassword(password);
    		newUser.setFileName(fileName);
    		newUser.setFileType(fileType);
    		newUser.setData(data);
    		userService.Save(newUser);
    		return ResponseEntity.ok(newUser);
    	}
    	else {
    		model.addAttribute("exists", true);
    		return ResponseEntity.ok(foundUser);
    	}
    }
    
    @PostMapping("/user/{username}/uploadFile")
    public Response uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        User imageFile = charityImageStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(imageFile.getFileName())
                .toUriString();

        return new Response(imageFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }
    
    @PutMapping("/user/{username}")
	public ResponseEntity<User> putUser(@PathVariable(value="username") String username, @RequestBody User user) {
		// Saving to DB using an instance of the repo interface.
		User foundUser = userRepository.findByUsername(username).orElse(null);
		
		// RespEntity crafts response to include correct status codes.
		if(foundUser == null) {
			return ResponseEntity.notFound().header("Message",  "No account found with that username").build();
		}
		else {
			foundUser.setUsername(user.getUsername());
			foundUser.setPassword(user.getPassword());
			foundUser.setCharityCat(user.getCharityCat());
			foundUser.setCharityTitle(user.getCharityTitle());
			foundUser.setCharityName(user.getCharityName());
			foundUser.setCharityStreet(user.getCharityStreet());
			foundUser.setCharityCity(user.getCharityCity());
			foundUser.setCharityState(user.getCharityState());
			foundUser.setCharityZip(user.getCharityZip());
			foundUser.setCharityPhone(user.getCharityPhone());
			foundUser.setAboutUs(user.getAboutUs());
			foundUser.setFileName(user.getFileName());
			foundUser.setFileType(user.getFileType());
			foundUser.setData(user.getData());
			userRepository.save(foundUser);
		}
		return ResponseEntity.ok(foundUser);
	}
    
    @DeleteMapping("/user/{username}")
	public ResponseEntity<User> deleteUser(@PathVariable(value="username") String username) {
		User foundUser = userRepository.findByUsername(username).orElse(null);
		
		if(foundUser == null) {
			return ResponseEntity.notFound().header("Message",  "No account with that username").build();
		}
		else {
			userRepository.delete(foundUser);
		}
		return ResponseEntity.ok().build();
	}
}