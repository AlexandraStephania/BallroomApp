package com.myapp.dto;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import com.myapp.model.Menu;
import com.myapp.model.Salon;

public class BallroomDTO {
    private Long id;

	private String ballroomName;

	private Collection<Menu> menus;

	private Collection<Salon> salons;

	@NotNull
	private Long contactId;

	public String getBallroomName() {
		return ballroomName;
	}

	public Long getContactId() {
		return contactId;
	}

	public Long getId() {
		return id;
	}

	public Collection<Menu> getMenus() {
		return menus;
	}

	public Collection<Salon> getSalons() {
		return salons;
	}

	public void setBallroomName(String ballroomName) {
		this.ballroomName = ballroomName;
	}
    
    public void setContactId(Long contactId) {
		this.contactId = contactId;
	}
    
    public void setId(Long id) {
		this.id = id;
	}
    
    public void setMenus(Collection<Menu> menus) {
		this.menus = menus;
	}
    
    public void setSalons(Collection<Salon> salons) {
		this.salons = salons;
	}
    
    
    
}
