package com.volapp.events;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;


@Entity
@Table(name="events")
public class Events{


    private String eventName;
    private String eventStreet;
    private String eventCity;
    private String eventState;
    private String eventZip;
    private String eventDateTime;
    private Calendar eventTime;
    private String eventDescription;
    private String charityName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    
    public Events() {}
    
    public Events(Events events) {
    	this.id = events.id;
    	this.charityName = events.charityName;
    	this.eventName = events.eventName;
    	this.eventStreet = events.eventStreet;
    	this.eventCity = events.eventCity;
    	this.eventState = events.eventState;
    	this.eventZip = events.eventZip;
//    	this.eventDateTime = events.eventDateTime;
    	this.eventTime = events.eventTime;
    	this.eventDescription = events.eventDescription;
    }
    
    public Events (Long id, String charityName, String eventName, String eventStreet, String eventCity, String eventState, String eventZip, String eventDateTime, Calendar eventTime, String eventDescription) {
    	this.id = id;
    	this.charityName = charityName;
    	this.eventName = eventName;
    	this.eventStreet = eventStreet;
    	this.eventCity = eventCity;
    	this.eventState = eventState;
    	this.eventZip = eventZip;
    	this.eventDateTime = eventDateTime;
    	this.eventTime = eventTime;
    	this.eventDescription = eventDescription;
    }
    


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCharityName() {
		return charityName;
	}


	public void setCharityName(String charityName) {
		this.charityName = charityName;
	}


	public String getEventName() {
		return eventName;
	}


	public void setEventName(String eventName) {
		this.eventName = eventName;
	}


	public String getEventStreet() {
		return eventStreet;
	}

	public void setEventStreet(String eventStreet) {
		this.eventStreet = eventStreet;
	}
	
	public String getEventCity() {
		return eventCity;
	}

	public void setEventCity(String eventCity) {
		this.eventCity = eventCity;
	}

	public String getEventState() {
		return eventState;
	}

	public void setEventState(String eventState) {
		this.eventState = eventState;
	}

	public String getEventZip() {
		return eventZip;
	}

	public void setEventZip(String eventZip) {
		this.eventZip = eventZip;
	}

	public String getEventDateTime() {
		return eventDateTime;
	}

	public void setEventDateTime(String eventDateTime) {
		this.eventDateTime = eventDateTime;
	}
	
	public Calendar convertDateTime(String eventDateTime) throws Exception{
		Calendar eventDate = Calendar.getInstance();
		eventDate.clear();
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date nowDate = format.parse(eventDateTime);
			eventDate.setTime(nowDate);
		} catch (Exception ex) {
			System.out.printf("%s can't be formatted!%n", eventDateTime);
		}
		return eventDate;
	}

	
	public Calendar getEventTime() throws Exception {
		try {
			eventTime = Events.this.convertDateTime(Events.this.eventDateTime);
		} catch(Exception ex) {
			System.out.println("Was not able to complete event day and time conversion. Caught exception: " + ex);
		}
		return eventTime; 
	}
	
	public void setEventTime(Calendar eventTime) {
		
		this.eventTime = eventTime; 
		
	}
	


	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
    
}
