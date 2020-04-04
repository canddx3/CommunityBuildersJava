package com.volapp.charityuser;

import java.util.List;

import javax.validation.Valid;

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
@RequestMapping("/api/charity")
public class UserController {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	UserIdRepository userIdRepo;
	
	@Autowired
	private MySQLUserDetailsService userService;
	
	@GetMapping("/user/{id}")
	public User findUser(@PathVariable(value="id") Long id) {
		return userIdRepo.findById(id);
	}
	
	@GetMapping("/user")
	public List<User> findAll() {
		return userRepo.findAll();
	}
	
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
//	User newUser = new User(user.getId(), user.getCharityName(), user.getCharityCat(), user.getCharityStreet(), user.getCharityCity(), user.getCharityState(), user.getCharityTitle(), user.getCharityZip(), user.convertPhone(user.getCharityPhone()), user.getUsername(), user.getPassword(), user.getCharityLogoLink());
	userService.Save(user);
	return ResponseEntity.ok().body(user);
	}

    @PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value="id") Long id, @Valid @RequestBody User user) {
		// Saving to DB using an instance of the repo interface.
		User foundUser = userIdRepo.findById(id);
		
		// RespEntity crafts response to include correct status codes.
		if(foundUser == null) {
			return ResponseEntity.notFound().header("Message",  "No account found with that username").build();
		}
		else {
			foundUser.setId(user.getId());
			foundUser.setUsername(user.getUsername());
			foundUser.setPassword(user.getPassword());
			foundUser.setCharityCat(user.getCharityCat());
			foundUser.setCharityTitle(user.getCharityTitle());
			foundUser.setCharityName(user.getCharityName());
			foundUser.setCharityStreet(user.getCharityStreet());
			foundUser.setCharityCity(user.getCharityCity());
			foundUser.setCharityState(user.getCharityState());
			foundUser.setCharityZip(user.getCharityZip());
			foundUser.setCharityPhone(user.convertPhone(user.getCharityPhone()));
			userRepo.save(foundUser);
		}
		return ResponseEntity.ok(foundUser);
	}
    
    @DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable(value="id") Long id) {
		User foundUser = userIdRepo.findById(id);
		
		if(foundUser == null) {
			return ResponseEntity.notFound().header("Message",  "No account with that username").build();
		}
		else {
			userRepo.delete(foundUser);
		}
		return ResponseEntity.ok().build();
	}
}
