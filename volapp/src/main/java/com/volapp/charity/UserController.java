package com.volapp.charity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MySQLUserDetailsService userService;
	
	
    @PostMapping()
    public void createUser( String username, String password, Model model) {
    	User foundUser = userRepository.findByUsername(username);
    	if (foundUser == null) {
    		User newUser = new User();
    		newUser.setUsername(username);
    		newUser.setPassword(password);
    		userService.Save(newUser);
    	}
    	else {
    		model.addAttribute("exists", true);
    	}
    }
}
