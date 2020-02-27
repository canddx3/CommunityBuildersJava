package com.volapp.charity;

import javax.persistence.*;

@Entity
@Table(name="volunteers")
public class Volunteer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String volunteerUsername;
	private String volunteerPassword;
	private String volunteerEmail;
	
	private String volunteerFirstName;
	private String volunteerLastName;
	private String volunteerStreet;
	private String volunteerCity;
	private String volunteerState;
	private String volunteerZip;
	private Long volunteerPhone;
	
	public Volunteer() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getVolunteerUsername() {
		return volunteerUsername;
	}
	public void setVolunteerUsername(String volunteerUsername) {
		this.volunteerUsername = volunteerUsername;
	}
	public String getVolunteerPassword() {
		return volunteerPassword;
	}
	public void setVolunteerPassword(String volunteerPassword) {
		this.volunteerPassword = volunteerPassword;
	}
	public String getVolunteerEmail() {
		return volunteerEmail;
	}
	public void setVolunteerEmail(String volunteerEmail) {
		this.volunteerEmail = volunteerEmail;
	}
	public String getVolunteerFirstName() {
		return volunteerFirstName;
	}
	public void setVolunteerFirstName(String volunteerFirstName) {
		this.volunteerFirstName = volunteerFirstName;
	}
	public String getVolunteerLastName() {
		return volunteerLastName;
	}
	public void setVolunteerLastName(String volunteerLastName) {
		this.volunteerLastName = volunteerLastName;
	}
	public String getVolunteerStreet() {
		return volunteerStreet;
	}
	public void setVolunteerStreet(String volunteerStreet) {
		this.volunteerStreet = volunteerStreet;
	}
	public String getVolunteerCity() {
		return volunteerCity;
	}
	public void setVolunteerCity(String volunteerCity) {
		this.volunteerCity = volunteerCity;
	}
	public String getVolunteerState() {
		return volunteerState;
	}
	public void setVolunteerState(String volunteerState) {
		this.volunteerState = volunteerState;
	}
	public String getVolunteerZip() {
		return volunteerZip;
	}
	public void setVolunteerZip(String volunteerZip) {
		this.volunteerZip = volunteerZip;
	}
	public Long getVolunteerPhone() {
		return volunteerPhone;
	}
	public void setVolunteerPhone(Long volunteerPhone) {
		this.volunteerPhone = volunteerPhone;
	}
	

}
