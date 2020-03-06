package com.volapp.charity;

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
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities());
	}
	
//	@Override
	public UserDetails loadVolunteerByUsername(String volunteerUsername) throws UsernameNotFoundException {
		Volunteer volunteer = volunteerRepository.findByVolunteerUsername(volunteerUsername);
		if (volunteer == null) {
			throw new UsernameNotFoundException(volunteerUsername);
		}
		return new org.springframework.security.core.userdetails.User(volunteer.getVolunteerUsername(), volunteer.getVolunteerPassword(), getAuthorities());
	}

	public UserDetails Save(User newUser) {
		newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
		User savedUser = userRepository.save(newUser);
		return new org.springframework.security.core.userdetails.User(savedUser.getUsername(), savedUser.getPassword(), getAuthorities());
	}

	public UserDetails Save(Volunteer newVolunteer) {
		newVolunteer.setVolunteerPassword(passwordEncoder.encode(newVolunteer.getVolunteerPassword()));
		Volunteer savedVolunteer = volunteerRepository.save(newVolunteer);
		return new org.springframework.security.core.userdetails.User(savedVolunteer.getVolunteerUsername(), savedVolunteer.getVolunteerPassword(), getAuthorities());
	}

	private List<SimpleGrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authList;
	}
	
}
