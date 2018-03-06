package com.myapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="contacts")
public class Contact {
	
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
    
	@Column(name="telephone", unique = true)
	private String telephone;
	
	@Column(name="address")
	private String address;
	
	@NotBlank
	@Email
	@Column(name = "email",unique = true)
	private String email;
	
	@Column(name = "site" ,unique = true)
	private String site;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "ballroomId")
	private Ballroom ballroom;

	/*@JsonManagedReference
	@OneToMany(mappedBy = "contact",  cascade=CascadeType.ALL)
    private Collection<Ballroom> ballrooms = new LinkedList<Ballroom>();
    */
	
	

	public Contact(Long id) {
		this.id=id;
	}

	public Contact() {
		
	}

	public String getAddress() {
		return address;
	}

	
	public String getEmail() {
		return email;
	}

	public Long getId() {
		return id;
	}

	public String getSite() {
		return site;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public Ballroom getBallroom() {
		return ballroom;
	}

	public void setBallroom(Ballroom ballroom) {
		this.ballroom= ballroom;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
}
