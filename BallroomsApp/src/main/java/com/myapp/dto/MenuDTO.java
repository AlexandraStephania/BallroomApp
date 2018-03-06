package com.myapp.dto;

import javax.validation.constraints.NotNull;

public class MenuDTO {

	private Long id;
	
	private String menuName;
	
	private String menuType;
	
	private double price;
	
	private String description;
	
	@NotNull
	private Long ballroomId;

	public Long getBallroomId() {
		return ballroomId;
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}

	public String getMenuName() {
		return menuName;
	}

	public String getMenuType() {
		return menuType;
	}

	public double getPrice() {
		return price;
	}

	public void setBallroomId(Long ballroomId) {
		this.ballroomId = ballroomId;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
