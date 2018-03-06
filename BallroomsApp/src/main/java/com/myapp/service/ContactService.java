package com.myapp.service;

import java.util.List;

import com.myapp.model.Contact;



public interface ContactService {
	
	public Contact findContact(Long contactId);

	public Contact findContact(String email);
	
	public List<Contact> findAllContacts();

	public Contact add(Contact contact);

	public Contact update(Contact contact);

	public void delete(Long contactId);
	
	
}
