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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.exception.DuplicateRecordException;
import com.myapp.model.Ballroom;
import com.myapp.service.BallroomService;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "false", methods = {
		RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.OPTIONS,
		RequestMethod.HEAD })
public class BallroomController {

	private final Logger logger = LoggerFactory.getLogger(BallroomController.class);

	@Autowired
	private BallroomService ballroomService;

	
	@ResponseBody
	@RequestMapping(value = "/ballrooms", method = RequestMethod.GET, produces = "application/json")
	public List<Ballroom> getBallrooms(Model model) {
		List<Ballroom> ballrooms = ballroomService.findAllBallrooms();
		return ballrooms;
		
	} 
	
	@ResponseBody
	@RequestMapping(value = "/ballroom", method = RequestMethod.GET,  produces = "application/json")
	public Ballroom getBallroomById(Model model,  @ModelAttribute("id") Long id) {
		Ballroom ballroom = ballroomService.findBallroom(id);
		return ballroom;
	}
	
	@ResponseBody
	@RequestMapping(value = "/ballroom/search/{term}",  method = RequestMethod.GET,  produces = "application/json")
	public List <Ballroom> search(@Valid @PathVariable("term") String term) {
		    List<Ballroom> result =  ballroomService.searchBallroom(term);
		    return result;
	}
	

	/**
	 * This method will be called on form submission, handling POST request for add
	 * ballroom in database. It also validates the ballroom input
	 */
	@RequestMapping(value = "/ballroom/add", method = RequestMethod.POST)
	public void addBallroomForm(@Valid @RequestBody Ballroom ballroom, BindingResult result,
			RedirectAttributes redirectAttributes) {
	    
		if (result.hasErrors()) {
			logger.error("Add ballroom error: " + result.getAllErrors());
		} else {
			try {
				ballroomService.add(ballroom);
			} catch (DuplicateRecordException e) {
				result.rejectValue("name", "duplicate", "Name already used");
				logger.error("Add ballroom error: " + result.getAllErrors());

			}
			redirectAttributes.addFlashAttribute("message", "Successfully added..");

		}
		
	}
	
	
	@RequestMapping(value = "/ballroom/update", method = RequestMethod.PUT)
	public void updateBallroom(@Valid @RequestBody Ballroom ballroom,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Update ballroom error: " + result.getAllErrors());
		} else {
			try {
				ballroomService.update(ballroom);
			}catch(DuplicateRecordException e) {
				result.rejectValue("name", "duplicate", "Ballroom already used");
				logger.error("UPDATE ballroom error: " + result.getAllErrors());
				
			}
			redirectAttributes.addFlashAttribute("message", "Successfully added..");
		}
	}

	@RequestMapping(value = "/ballroom/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteBallroom(@Valid @ModelAttribute("id") Long id, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {
			ballroomService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Successfully deleted..");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Delete error: " + e.getMessage());
		}
	}
}
