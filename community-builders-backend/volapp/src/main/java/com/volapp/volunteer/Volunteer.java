package com.volapp.volunteer;

import javax.persistence.*;

@Entity
@Table(name="volunteers")
public class Volunteer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String username;
	private String password;
	private String email;
	
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String state;
	private String zip;
	private Long phone;
	
	public Volunteer() {
		
	}
	
	public Volunteer(Volunteer volunteer) {
		this.id = volunteer.id;
		this.username = volunteer.username;
		this.password = volunteer.password;
		this.firstName = volunteer.firstName;
		this.lastName = volunteer.lastName;
		this.email = volunteer.email;
		this.phone = volunteer.phone;
		this.street = volunteer.street;
		this.city = volunteer.city;
		this.state = volunteer.state;
		this.zip = volunteer.zip;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public Volunteer orElse(Volunteer volunteer) {
		// TODO Auto-generated method stub
		return null;
	}

}
