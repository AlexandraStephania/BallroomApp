package com.myapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="salonImages")
public class SalonImages {
  
	@Id
	@GeneratedValue( strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="imageName")
	private String imageName;
	
	
	@Column(name="location")
	private String location;

}
