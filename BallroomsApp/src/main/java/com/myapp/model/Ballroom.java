package com.myapp.model;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ballrooms")
public class Ballroom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "ballroomName")
	private String ballroomName;

	@NotBlank
	@Column(name = "description", length = 6000)
	private String description;

	@JsonManagedReference
	@OneToMany(mappedBy = "ballroom", cascade = CascadeType.ALL)
	private Collection<Contact> contacts = new LinkedList<Contact>();

	@JsonManagedReference
	@OneToMany(mappedBy = "ballroom", cascade = CascadeType.ALL)
	private Collection<Menu> menus = new LinkedList<Menu>();

	@JsonManagedReference
	@OneToMany(mappedBy = "ballroom", cascade = CascadeType.ALL)
	private Collection<Salon> salons = new LinkedList<Salon>();

	public Ballroom() {

	}

	public Ballroom(Long ballroomId) {
		this.id = ballroomId;
	}

	public String getBallroomName() {
		return ballroomName;
	}

	/*
	 * @JsonBackReference
	 * 
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "contactId") private Contact contact;
	 */

	public Collection<Contact> getContacts() {
		return contacts;
	}

	public String getDescription() {
		return description;
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

	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}

	public void setDescription(String description) {
		this.description = description;
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
