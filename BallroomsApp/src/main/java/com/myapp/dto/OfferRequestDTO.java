package com.myapp.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class OfferRequestDTO {
	private Long id;
	
	private String type;
	
	private int guestsNumber;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date eventDate;
	
	private String mesage;
	
	private String name;
	
	private String email;
	
	private String phone;
	
	@NotNull
	private Long salonId;

	public String getEmail() {
		return email;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public int getGuestsNumber() {
		return guestsNumber;
	}

	public Long getId() {
		return id;
	}

	public String getMesage() {
		return mesage;
	}

	public String getName() {
		return name;
	}

	public String getPhone() {
		return phone;
	}

	public Long getSalonId() {
		return salonId;
	}

	public String getType() {
		return type;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public void setGuestsNumber(int guestsNumber) {
		this.guestsNumber = guestsNumber;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setSalonId(Long salonId) {
		this.salonId = salonId;
	}

	public void setType(String type) {
		this.type = type;
	}
}
