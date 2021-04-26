package com.volapp.charity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="charity")
public class Charity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@NotEmpty(message = "cant be empty")
    private String username;
	@NotEmpty(message = "cant be empty")
	private String password;
	@NotEmpty(message = "cant be empty")
    private String charityName;
	@NotEmpty(message = "cant be empty")
    private String charityCat;
	@NotEmpty(message = "cant be empty")
	private String charityPhone;
	@NotEmpty(message = "cant be empty")
    private String charityAddress;

    public Charity() {}
    
    public Charity(Charity charity) {
    	this.id = charity.id;
		this.username = charity.username;
		this.password = charity.password;
    	this.charityName = charity.charityName;
		this.charityPhone = charity.charityPhone;
    	this.charityCat = charity.charityCat;
    	this.charityAddress = charity.charityAddress;

    }

    public Charity(Long id, String username, String password, String charityName, String charityPhone, String charityCat, String charityAddress) {
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
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
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

