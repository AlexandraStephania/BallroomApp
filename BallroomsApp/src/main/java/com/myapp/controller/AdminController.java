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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.exception.DuplicateRecordException;
import com.myapp.model.Admin;
import com.myapp.service.AdminService;

@Controller
public class AdminController {

	private final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/admins", method = RequestMethod.GET)
	public String getAdmins(Model model) {
		List<Admin> admins = adminService.findAllAdmins();
		model.addAttribute("admins", admins);
		return "admins";
	}
	

	@RequestMapping(value = "/admin/add", method = RequestMethod.GET)
	public String getAddAdminForm(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "add-admin";
	}

	/**
	 * This method will be called on form submission, handling POST request for add
	 * admin in database. It also validates the admin input
	 */
	@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	public String addAdminForm(@Valid @ModelAttribute("admin") Admin admin, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			logger.error("Add admin error: " + result.getAllErrors());
			return "add-admin";
		} else {
			try {
				adminService.add(admin);
			} catch (DuplicateRecordException e) {
				result.rejectValue("name", "duplicate", "admin already used");
				logger.error("Add admin error: " + result.getAllErrors());
				return "add-admin";
			}
			redirectAttributes.addFlashAttribute("message", "Successfully added..");
			return "redirect:/admins";
		}
	}

	@RequestMapping(value = "/admin/update", method = RequestMethod.GET)
	public String getEditAdminForm(Model model, @RequestParam(value = "id", required = true) Long id,
			RedirectAttributes redirectAttributes) {
		Admin admin = adminService.findAdmin(id);
		if (admin != null) {
			model.addAttribute("admin", admin);
			return "update-admin";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "admin not found");
			return "redirect:/admins";
		}

	}

	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public String updateAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult result) {
		if (result.hasErrors()) {
			logger.error("Update admin error: " + result.getAllErrors());
			return "update-admin";
		} else {
			try {
				adminService.update(admin);
			} catch (DuplicateRecordException e) {
				result.rejectValue("name", "duplicate", "New username already used by other admin");
				logger.error("Update admin error: " + result.getAllErrors());
				return "update-admin";
			}
			return "redirect:/admins";
		}
	}

	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public String deleteAdmin(@Valid @ModelAttribute("id") Long id, BindingResult result,
			RedirectAttributes redirectAttributes) {
		try {
			adminService.delete(id);
			redirectAttributes.addFlashAttribute("message", "Successfully deleted..");
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Delete error: " + e.getMessage());
		}

		return "redirect:/admins";
	}
}
