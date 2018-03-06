package com.myapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.myapp.exception.DuplicateRecordException;
import com.myapp.exception.RecordNotFoundException;
import com.myapp.model.Contact;
import com.myapp.repository.ContactRepository;

@Service("contactService")
public class ContactServiceImpl implements ContactService {

	private static final Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

	@Autowired
	private ContactRepository contactRepository;
   
	
	@Override
	public Contact findContact(Long contactId) {
		Contact contact = contactRepository.findOne(contactId);
		return contact;
	}

	@Override
	public Contact findContact(String email) {
		return contactRepository.findByEmail(email);

	}

	@Override
	public List<Contact> findAllContacts() {
		return contactRepository.findAll();
	}

	

	@Override
	public Contact add(Contact contact) {
		// Check if already exist a contact with same email.
		Contact existingContact = contactRepository.findByEmail(contact.getEmail());
		if (existingContact != null) {
			String errorMessage = "Already exists a contact with same email: " + contact.getEmail();
			logger.error(errorMessage);
			throw new DuplicateRecordException(errorMessage);
		}
		contactRepository.save(contact);
		return contact;
	}

	@Override
	public Contact update(Contact contact) {
		Contact existingContact = contactRepository.findOne(contact.getId());
		if (existingContact == null) {
			String errorMessage = "Contact with id " + contact.getId() + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}

		// Check if email was changed.
		if (!existingContact.getEmail().equals(contact.getEmail())) {
			// Email changed, check again if new email already exists
			if (contactRepository.findByEmail(contact.getEmail()) != null) {
				String errorMessage = "The new email already used by other contact: " + contact.getEmail();
				logger.error(errorMessage);
				throw new DuplicateRecordException(errorMessage);
			}
		}
		return contactRepository.save(contact);
	}

	@Override
	public void delete(Long contactId) {
		Contact contact = contactRepository.findOne(contactId);
		logger.debug("Delete contact with id: " + contactId);

		if (contact != null) {
			try {
				contactRepository.delete(contactId);
			} catch (DataIntegrityViolationException ex) {
				String errorMessage = "Can not delete contact because is assigned";
				logger.error(errorMessage);
				throw new DataIntegrityViolationException(errorMessage);
			}
		} else {
			String errorMessage = "Contact with id " + contactId + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}

	}


	
}
