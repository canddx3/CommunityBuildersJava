package com.volapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.volapp.charity.MySQLUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private MySQLUserDetailsService mySQLUserDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(mySQLUserDetailsService).passwordEncoder(passwordEncoder());
	}

	
	 @Override 
	 protected void configure(HttpSecurity http) throws Exception {
	 http.authorizeRequests() .antMatchers("/", "/user", "/home").permitAll()
	 .anyRequest() .authenticated() .and() .formLogin() .loginPage("/Login")
	 .permitAll() .and() .logout() .permitAll(); 
	 }
	 

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.httpBasic().and()
//	       .authorizeRequests()
//	       .antMatchers("/" , "/user")
//	       .permitAll().anyRequest().authenticated()
//	       .and().csrf().disable();
//	}

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//		.httpBasic().and()
//	       .authorizeRequests()
//	       .antMatchers("/charity" , "/api/charity/**")
//	       .permitAll().anyRequest().authenticated()
//	       .and().csrf().disable();
//	} 

}
