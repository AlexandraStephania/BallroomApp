package com.myapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.exception.DuplicateRecordException;
import com.myapp.model.Contact;
import com.myapp.service.ContactService;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "false", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS,
		RequestMethod.HEAD })
public class ContactController {

	private final Logger logger = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService contactService;
    
	@ResponseBody
	@RequestMapping(value = "/contacts", method = RequestMethod.GET, produces = "application/json")
	public List<Contact> getContacts(Model model) {
		List<Contact> contacts = contactService.findAllContacts();
		return contacts;
	}
	
	
		
	
	@ResponseBody
	@RequestMapping(value = "/contact", method = RequestMethod.GET,  produces = "application/json")
	public Contact getContactById(Model model,  @ModelAttribute("id") Long id) {
		Contact contact = contactService.findContact(id);
		return contact;
	}
	
	

	/**
	 * This method will be called on form submission, handling POST request for add
	 * contact in database. It also validates the contact input
	 */
	@RequestMapping(value = "/contact/add", method = RequestMethod.POST)
	public void  addContactForm(@Valid @RequestBody  Contact contact, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Add contact error: " + result.getAllErrors());
		
		} else {
			try {
				contactService.add(contact);
			} catch (DuplicateRecordException e) {
				result.rejectValue("name", "duplicate", "Email already used");
				logger.error("Add contact error: " + result.getAllErrors());
				
			}
			redirectAttributes.addFlashAttribute("message", "Successfully added..");
			
		}
	}



	@RequestMapping(value = "/contact/update", method = RequestMethod.PUT)
	public void  updateContact(@Valid @RequestBody Contact contact, BindingResult result, 
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("UPDATE  contact error: " + result.getAllErrors());
		} else {
			try {
				contactService.update(contact);
			} catch (DuplicateRecordException e) {
				result.rejectValue("name", "duplicate", "Contact already used");
				logger.error("UPDATE contact error: " + result.getAllErrors());
				
			}
			redirectAttributes.addFlashAttribute("message", "Successfully added..");
			
		
		}
	}

	@RequestMapping(value = "/contact/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteContact(@Valid @ModelAttribute("id") Long id, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {
			contactService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Successfully deleted..");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Delete error: " + e.getMessage());
		}

	}
}
