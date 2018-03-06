package com.myapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="menus")
public class Menu {
   
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotBlank
	@Column(name = "menuName", length = 60)
	private String menuName;
	
	@NotBlank
	@Column(name = "menuType", length = 60)
	private String menuType;
	
	@Range(min=10, max=5000)
	@Column(name = "price")
	private double price;
	
	
	@NotBlank
	@Column(name = "description", length = 6000)
	private String description;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "ballroomId")
	private Ballroom ballroom;


	public Ballroom getBallroom() {
		return ballroom;
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


	public void setBallroom(Ballroom ballroom) {
		this.ballroom = ballroom;
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
