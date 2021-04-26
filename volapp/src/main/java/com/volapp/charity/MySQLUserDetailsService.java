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

@Service
public class MySQLUserDetailsService implements UserDetailsService{

	@Autowired
	private CharityRepository charityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Charity charity = charityRepository.findByUsername(username);
		if (charity == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(charity.getUsername(), charity.getPassword(), getAuthority());
	}

	public UserDetails Save(Charity newCharity) {
		newCharity.setPassword(passwordEncoder.encode(newCharity.getPassword()));
		Charity savedCharity = charityRepository.save(newCharity);
		return new org.springframework.security.core.userdetails.User(savedCharity.getUsername(), savedCharity.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authList;
	}
}