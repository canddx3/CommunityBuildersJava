package com.volapp.charityuser;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.volapp.volunteer.Volunteer;
import com.volapp.volunteer.VolunteerRepository;


@Service
public class MySQLUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VolunteerRepository volunteerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
	}

	public UserDetails Save(User newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		User savedUser = userRepository.save(newUser);
		return new org.springframework.security.core.userdetails.User(savedUser.getUsername(), savedUser.getPassword(), getAuthority());
	}
	
	public UserDetails loadVolunteerByUsername(String username) throws UsernameNotFoundException {
		Volunteer volunteer = volunteerRepository.findByUsername(username);
		if (volunteer == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(volunteer.getUsername(), volunteer.getPassword(), getAuthority());
	}
	
	public UserDetails Save(Volunteer newVolunteer) {
		newVolunteer.setPassword(passwordEncoder.encode(newVolunteer.getPassword()));
		Volunteer savedVolunteer = volunteerRepository.save(newVolunteer);
		return new org.springframework.security.core.userdetails.User(savedVolunteer.getUsername(), savedVolunteer.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authList;
	}
}