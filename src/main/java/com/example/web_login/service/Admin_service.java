package com.example.web_login.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.web_login.entity.Serevice_add;

@Service
public interface Admin_service {

	public void register(Serevice_add serevice_add) ;
	
	 List<Serevice_add> findAll();
	 
}
