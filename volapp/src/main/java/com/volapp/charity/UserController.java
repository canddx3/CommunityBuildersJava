package com.volapp.charity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
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
	
	@RequestMapping("/home")
	public String hope() {
		return "home.html";
	}
	
	@GetMapping("/user")
	public List<User> getUsers(){
		List<User> foundRepos = userRepository.findAll();
		return foundRepos;
	}
	
    @PostMapping("/user")
    public String createUser(@RequestParam("charityPhone") Long charityPhone ,@RequestParam("charityZip") Long charityZip ,@RequestParam("charityState") String charityState ,@RequestParam("charityCity") String charityCity ,@RequestParam("charityStreet") String charityStreet ,@RequestParam("charityCat") String charityCat ,@RequestParam("charityName") String charityName ,@RequestParam("charityTitle") String charityTitle ,@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
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
    		userService.Save(newUser);
    		return "Login";
    	}
    	else {
    		model.addAttribute("exists", true);
    		return "user";
    	}
    }
}