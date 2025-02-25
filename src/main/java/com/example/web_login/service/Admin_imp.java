package com.example.web_login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.example.web_login.entity.Serevice_add;
import com.example.web_login.repo.Admin_repo;

@Service
public class Admin_imp implements Admin_service{

	
	@Autowired
	private Admin_repo admin_repo;
	@Override
	public void register(Serevice_add serevice_add) {
		
		admin_repo.save(serevice_add);
	}
	@Override
	public List<Serevice_add> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
