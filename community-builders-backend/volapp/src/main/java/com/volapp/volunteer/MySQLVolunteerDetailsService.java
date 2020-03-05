package com.volapp.volunteer;

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
public class MySQLVolunteerDetailsService implements UserDetailsService{
	
	@Autowired
	private VolunteerRepository volunteerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
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