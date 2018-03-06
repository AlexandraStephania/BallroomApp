package com.myapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="offersRequest")
public class OfferRequest {
   
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotBlank
	@Column(name = "type", length = 60)
	private String type;
	
	@Min(30)
	@Max(500)
	@Column(name = "guestsNumber")
	private int guestsNumber;
	
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "eventDate")
	private Date eventDate;
    
	@NotBlank
	@Column(name = "mesage", length = 600)
	private String mesage;
	
	
	@Size(min=3 , max= 40)
	@Column(name = "name", length = 64)
	private String name;


	@NotBlank
	@Email
	@Column(name = "email", unique = true)
	private String email;


	@NotBlank
	@Column(name = "phone", unique = true)
	private String phone;

	@ManyToOne
  	@JoinColumn(name = "salonId")
  	@JsonBackReference
  	private Salon salon;
	
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


	public Salon getSalon() {
		return salon;
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


	public void setSalon(Salon salon) {
		this.salon = salon;
	}


	public void setType(String type) {
		this.type = type;
	}
	
}
