package com.myapp.dto;

import javax.validation.constraints.NotNull;

public class SalonDTO {
	private Long id;
	
	private String salonName;
	
	private String address;
	
	private int capacity;
	
	private int surface;
	
	private boolean danceFloor;
	
	private boolean terrace;
	
	private boolean parking;
	
	@NotNull
	private Long ballroomId;
	
	public String getAddress() {
		return address;
	}
	public Long getBallroomId() {
		return ballroomId;
	}
	public int getCapacity() {
		return capacity;
	}
	public Long getId() {
		return id;
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
	public void setBallroomId(Long ballroomId) {
		this.ballroomId = ballroomId;
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
