package com.myapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admins")
public class Admin {
    
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="userName",unique = true)
	private String userName;
	
	@Column(name="password",  unique = true)
	private String password;


	public Long getId() {
		return id;
	}
	
	public String getPassword() {
		return password;
	}
	

	public String getUserName() {
		return userName;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
