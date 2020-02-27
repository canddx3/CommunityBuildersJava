package com.volapp.charity;

import javax.persistence.*;
import com.volapp.charity.User;

@Entity
@Table(name="events")
public class Event extends User {
	
	@Column
	private String username;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long eventID;
	
	private String eventName;
	private String eventDescription;
	private String eventMonth;
	private String eventDay;
	private String eventYear;
	
	
	public Event() {
		super();
		
	}
	
	public Long getEventID() {
		return eventID;
	}

	public void setEventID(Long eventID) {
		this.eventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventMonth() {
		return eventMonth;
	}

	public void setEventMonth(String eventMonth) {
		this.eventMonth = eventMonth;
	}

	public String getEventDay() {
		return eventDay;
	}

	public void setEventDay(String eventDay) {
		this.eventDay = eventDay;
	}

	public String getEventYear() {
		return eventYear;
	}

	public void setEventYear(String eventYear) {
		this.eventYear = eventYear;
	}
	

}
