package com.myapp.model;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="salons")
public class Salon {
  
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotBlank
	@Column(name = "salonName", length = 60)
	private String salonName;
	
	@NotBlank
	@Column(name = "address", length = 100)
	private String address;
	

	@Column(name = "capacity")
	private int capacity;

	@Column(name = "surface")
	private int surface;

	@Column(name = "danceFloor")
	private boolean danceFloor;
	
	@Column(name = "terrace")
	private boolean terrace;
	
	@Column(name = "parking")
	private boolean parking;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "ballroomId")
	private Ballroom ballroom;
    
	@JsonManagedReference
	@OneToMany(mappedBy = "salon")
    private Collection<OfferRequest> offerRequests = new LinkedList<OfferRequest>();
    
	public Salon() {
		
	}
	public Salon(Long salonId) {
		this.id=salonId;
	}

	public String getAddress() {
		return address;
	}

	public Ballroom getBallroom() {
		return ballroom;
	}


	public int getCapacity() {
		return capacity;
	}
    
    public Long getId() {
		return id;
	}
    
	public Collection<OfferRequest> getOfferRequests() {
		return offerRequests;
	}

	public String getSalonName() {
		return salonName;
	}


	public int getSurface() {
		return surface;
	}

	public boolean isDanceFloor() {
		return danceFloor;
	}

	public boolean isParking() {
		return parking;
	}

	public boolean isTerrace() {
		return terrace;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setBallroom(Ballroom ballroom) {
		this.ballroom = ballroom;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setDanceFloor(boolean danceFloor) {
		this.danceFloor = danceFloor;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOfferRequests(Collection<OfferRequest> offerRequests) {
		this.offerRequests = offerRequests;
	}


	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public void setSalonName(String salonName) {
		this.salonName = salonName;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public void setTerrace(boolean terrace) {
		this.terrace = terrace;
	}
	
}
