package com.myapp.service;

import java.util.List;

import com.myapp.model.Admin;

public interface AdminService {
  
	public Admin findAdmin(Long adminId);

	public Admin findAdmin(String userName);
	
	public List<Admin> findAllAdmins();

	public Admin add(Admin admin);

	public Admin update(Admin admin);

	public void delete(Long adminId);
}
