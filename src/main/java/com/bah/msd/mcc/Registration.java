package com.bah.msd.mcc;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "REGISTRATIONS")
public class Registration {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "EVENT_ID")
	@JsonProperty("event_id")
	private String event_name;
	@Column(name = "CUSTOMER_ID")
	@JsonProperty("customer_id")
	private String customer_name;
	@Column(name = "REGISTRATION_DATE")
	@JsonProperty("registration_date")
	private Date registration_date;
	@Column(name = "NOTES")
	private String notes;

	public Registration() {
	}

	public Registration(Long id, String eventId, String customerId, Date registrationDate, String notes) {
		super();
		this.id = id;
		this.event_name = eventId;
		this.customer_name = customerId;
		this.registration_date = registrationDate;
		this.notes = notes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEventId() {
		return event_name;
	}

	public void setEventId(String eventId) {
		this.event_name = eventId;
	}

	public String getCustomerId() {
		return customer_name;
	}

	public void setCustomerId(String customerId) {
		this.customer_name = customerId;
	}

	public Date getRegistrationDate() {
		return registration_date;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registration_date = registrationDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}