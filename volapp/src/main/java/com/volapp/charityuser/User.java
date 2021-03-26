package com.volapp.charityuser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    private String charityName;
    private String charityCat;
	private String charityPhone;
    private String charityAddress;



    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    
    public User() {}
    
    public User(User user) {
    	this.id = user.id;
		this.username = user.username;
		this.password = user.password;
    	this.charityName = user.charityName;
		this.charityPhone = user.charityPhone;
    	this.charityCat = user.charityCat;
    	this.charityAddress = user.charityAddress;

    }

    public User(Long id, String username, String password, String charityName, String charityPhone, String charityCat, String charityAddress) {
    	this.id = id;
		this.username = username;
		this.password = password;
    	this.charityName = charityName;
		this.charityPhone = charityPhone;
		this.charityCat = charityCat;
    	this.charityAddress = charityAddress;
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
	public String getCharityName() {
		return charityName;
	}
	public void setCharityName(String charityName) {
		this.charityName = charityName;
	}
	public String getCharityCat() {
		return charityCat;
	}
	public void setCharityCat(String charityCat) {
		this.charityCat = charityCat;
	}
	public String getCharityAddress() { return charityAddress; }
	public void setCharityAddress(String charityAddress) { this.charityAddress = charityAddress; }
	public String getCharityPhone() { return charityPhone; }
	public void setCharityPhone(String charityPhone) {
		this.charityPhone = charityPhone;
	}
	public String convertPhone(String charityPhone) {
		return charityPhone.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
	}
	
	
}

