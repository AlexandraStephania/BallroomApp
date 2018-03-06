package com.myapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.myapp.exception.DuplicateRecordException;
import com.myapp.exception.RecordNotFoundException;
import com.myapp.model.Admin;
import com.myapp.repository.AdminRepository;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	private static final Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin findAdmin(Long adminId) {
		Admin admin = adminRepository.findOne(adminId);
		return admin;

	}

	@Override
	public Admin findAdmin(String userName) {
		return adminRepository.findByUserName(userName);
	}

	@Override
	public List<Admin> findAllAdmins() {
		return adminRepository.findAll();
	}

	@Override
	public Admin add(Admin admin) {
		// Check if already exist a user with same name.
		Admin existingAdmin= adminRepository.findByUserName(admin.getUserName());
		if (existingAdmin != null) {
			String errorMessage = "Already exists a user with same user name: " + admin.getUserName();
			logger.error(errorMessage);
			throw new DuplicateRecordException(errorMessage);
		}
		adminRepository.save(admin);
		return admin;
	}

	@Override
	public Admin update(Admin admin) {
		Admin existingAdmin = adminRepository.findOne(admin.getId());
		if (existingAdmin == null) {
			String errorMessage = "Admin with id " + admin.getId() + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}

		// Check if username was changed.
		if (!existingAdmin.getUserName().equals(admin.getUserName())) {
			// user name changed, check again if new user name already exists
			if (adminRepository.findByUserName(admin.getUserName()) != null) {
				String errorMessage = "The new userName already used by other admin: " + admin.getUserName();
				logger.error(errorMessage);
				throw new DuplicateRecordException(errorMessage);
			}
		}
		return adminRepository.save(admin);
	}

	@Override
	public void delete(Long adminId) {
		Admin admin= adminRepository.findOne(adminId);
		logger.debug("Delete admin with id: " + adminId);

		if (admin != null) {
			try {
				adminRepository.delete(adminId);
			} catch (DataIntegrityViolationException ex) {
				String errorMessage = "Can not delete admin because is assigned";
				logger.error(errorMessage);
				throw new DataIntegrityViolationException(errorMessage);
			}
		} else {
			String errorMessage = "admin with id " + adminId + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
	}

}
