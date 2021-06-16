package com.project.sellerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.sellerapp.model.RegisteredUser;
import com.project.sellerapp.repository.RegisteredUserRepository;



@Service
public class RegisteredUserService {

	
	@Autowired
	private RegisteredUserRepository repository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<RegisteredUser> findAll() {
		return repository.findAllRegisteredUser();
	}

	public RegisteredUser findOne(Long id) {
		return repository.findByIdAndActive(id, true).orElse(null);
	}

	public RegisteredUser findByEmail(String username) {
		// TODO Auto-generated method stub
		return repository.findByEmail(username);
	}

	public void save(RegisteredUser registeredUser) {
		repository.save(registeredUser);
		
	}
}
