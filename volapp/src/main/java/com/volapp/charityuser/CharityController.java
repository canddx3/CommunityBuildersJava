package com.volapp.charityuser;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/charity")
public class CharityController {

	@Autowired
	CharityRepository charityRepo;

	@Autowired
	CharityIdRepository charityIdRepo;

	@Autowired
	private MySQLUserDetailsService userService;

	@GetMapping
	public List<Charity> findAll() {
		return charityRepo.findAll();
	}

	@PostMapping
	public ResponseEntity<Charity> createUser(@Valid @RequestBody Charity charity) throws Exception {
		charityRepo.save(charity);
		return ResponseEntity.ok().body(charity);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Charity> findById(@PathVariable(value="id") long id) {
		Charity foundCharity = charityIdRepo.findById(id);
		if(foundCharity == null) {
			return ResponseEntity.notFound().header("Message", "No Charity Profile").build();
		}
		return ResponseEntity.ok(foundCharity);
	}

	@PutMapping("/profile/{id}")
	public ResponseEntity<Charity> updateUser(@PathVariable(value="id") Long id, @Valid @RequestBody Charity charity) {
		// Saving to DB using an instance of the repo interface.
		Charity charityUser = charityIdRepo.findById(id);
		// RespEntity crafts response to include correct status codes.
		if(charityUser == null) {
			return ResponseEntity.notFound().header("Message",  "No account found with that username").build();
		}
		else {
//			charityUser.setId(charityUser.getId());
//			charityUser.setUsername(charityUser.getUsername());
//			charityUser.setPassword(charityUser.getPassword());
			charityUser.setCharityCat(charityUser.getCharityCat());
			charityUser.setCharityName(charityUser.getCharityName());
			charityUser.setCharityAddress(charityUser.getCharityAddress());
			charityUser.setCharityPhone(charityUser.convertPhone(charityUser.getCharityPhone()));
			userService.Save(charityUser);
		}
		return ResponseEntity.ok(charityUser);
	}

    @DeleteMapping("/delete/{id}")
	public ResponseEntity<Charity> deleteUser(@PathVariable(value="id") Long id) {
		Charity foundUser = charityIdRepo.findById(id);

		if(foundUser == null) {
			return ResponseEntity.notFound().header("Message",  "No account with that username").build();
		}
		else {
			charityIdRepo.delete(foundUser);
		}
		return ResponseEntity.ok().build();
	}
}
