package com.myapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.dto.OfferRequestDTO;
import com.myapp.exception.DuplicateRecordException;
import com.myapp.model.OfferRequest;
import com.myapp.model.Salon;
import com.myapp.service.OfferRequestService;
import com.myapp.service.SalonService;

@Controller
public class OfferRequestController {
	private final Logger logger = LoggerFactory.getLogger(OfferRequestController.class);

	@Autowired
	private OfferRequestService offerRequestService;
	
	@Autowired
	private SalonService salonService;
	
	@ResponseBody
	@RequestMapping(value = "/offerRequests", method = RequestMethod.GET, produces = "application/json")
	public List<OfferRequest> getOfferRequests(Model model) {
		List<OfferRequest> offerRequests = offerRequestService.findAllOfferRequests();
		return offerRequests;
	}

	
	/**
	 * This method will be called on form submission, handling POST request for add
	 * menu in database. It also validates the input
	 */
	@RequestMapping(value = "/offerRequest/add", method = RequestMethod.POST)
	public void addOfferRequestForm(@Valid @RequestBody OfferRequest offerRequest,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Add offerRequest error: " + result.getAllErrors());
	
		} else {
			try {
				offerRequestService.add(offerRequest);
			} catch (DuplicateRecordException e) {
				result.rejectValue("name", "duplicate", " Request already used");
				logger.error("Add offer request error: " + result.getAllErrors());
				
			}
			redirectAttributes.addFlashAttribute("message", "Successfully added..");
			
		
		}
	}
	
	
	@RequestMapping(value = "/offerRequest/update", method = RequestMethod.GET)
	public String getEditOfferRequestForm(Model model, @RequestParam(value = "id", required = true) Long id,
			RedirectAttributes redirectAttributes) {
		OfferRequest offerRequest= offerRequestService.findOfferRequest(id);
		if (offerRequest != null) {
			// Create and put a offerRequestDTO needed to edit offerRequest.
			OfferRequestDTO offerRequestDTO = new OfferRequestDTO();
			offerRequestDTO.setId(offerRequest.getId());
			offerRequestDTO.setName(offerRequest.getName());
			offerRequestDTO.setEmail(offerRequest.getEmail());
			offerRequestDTO.setEventDate(offerRequest.getEventDate());
			offerRequestDTO.setGuestsNumber(offerRequest.getGuestsNumber());
			offerRequestDTO.setMesage(offerRequest.getMesage());
			offerRequestDTO.setPhone(offerRequest.getPhone());
			offerRequestDTO.setType(offerRequest.getType());
			offerRequestDTO.setSalonId(offerRequest.getSalon().getId());
			

			model.addAttribute("offerRequest", offerRequestDTO);

		
			List<Salon> salons = salonService.findAllSalons();
			model.addAttribute("salons", salons);

			return "update-offerRequest";
		} else {
			logger.error("Edit error: offerRequest with id {} not found", id);
			redirectAttributes.addFlashAttribute("errorMessage", "offerRequest not found");
			return "redirect:/offerRequests";
		}

	}
   
	

	@RequestMapping(value = "/offerRequest/update", method = RequestMethod.POST)
	public String updateOfferRequest(@Valid @ModelAttribute("offerRequest") OfferRequestDTO offerRequestDTO, Model model,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			List<Salon> salons = salonService.findAllSalons();
			model.addAttribute("salons", salons);

			return "update-offerRequest";
		} else {
			OfferRequest offerRequest  = new OfferRequest();
			offerRequest.setId(offerRequestDTO.getId());
			offerRequest.setName(offerRequestDTO.getName());
			offerRequest.setEmail(offerRequestDTO.getEmail());
			offerRequest.setEventDate(offerRequestDTO.getEventDate());
			offerRequest.setGuestsNumber(offerRequestDTO.getGuestsNumber());
			offerRequest.setMesage(offerRequestDTO.getMesage());
			offerRequest.setPhone(offerRequestDTO.getPhone());
			offerRequest.setType(offerRequestDTO.getType());
			offerRequest.setSalon(new Salon(offerRequestDTO.getSalonId()));
			offerRequestService.update(offerRequest);

			return "redirect:/offerRequests";
		}
	}
	
	@RequestMapping(value = "/offerRequest/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteOfferRequest(@Valid @ModelAttribute("id") Long id, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {
			offerRequestService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Successfully deleted..");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Delete error: " + e.getMessage());
		}

	}
	
 
	
	
}
