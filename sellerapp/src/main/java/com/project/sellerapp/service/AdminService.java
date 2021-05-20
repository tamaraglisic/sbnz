package com.project.sellerapp.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.sellerapp.model.Admin;
import com.project.sellerapp.model.Authority;
import com.project.sellerapp.repository.AdminRepository;
import com.project.sellerapp.repository.AuthorityRepository;



@Service
public class AdminService {

	
	@Autowired 
	private AdminRepository repository;
	
	@Autowired 
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<Admin> findAll() {
		return repository.findAllAdmin();
	}

	

	public Admin create(Admin entity) throws Exception {
		Admin admin = repository.findByEmail(entity.getEmail());
		
		Set<Authority> set = new HashSet<Authority>();
		set.add(authorityRepository.findByRole("ROLE_ADMIN"));
		if (admin != null)
			throw new Exception("Admin with given email already exist");
		Admin a = new Admin(entity.getFirstName(), entity.getLastName(), entity.getEmail(),  passwordEncoder.encode(entity.getPassword()), true, true, set);
	
        return repository.save(a);

	}

	
	public Admin update(Admin entity, Long id) throws Exception {
		Admin a = repository.findById(id).orElse(null);
		if(a == null)
			throw new Exception("Admin with given id doesn't exist");
//		validateAttributes(a);	
		String oldEmail = a.getEmail();
		Admin checkAdmin;
		if(!oldEmail.equals(entity.getEmail())) {
			checkAdmin = repository.findByEmail(entity.getEmail());
		    if(checkAdmin != null)
		    	throw new Exception("User with given email already exist");
		    a.setEmail(entity.getEmail());
		}
		else {
			a.setEmail(oldEmail);
		}
		
		
		
		return repository.save(a);
	}

	
//
//	@Override
//	public void delete(Long id) throws Exception {
//		Admin a = repository.findById(id).orElse(null);
//		if(a == null)
//			throw new Exception("Admin doesn't exist");
//		a.setActive(false);
//		a = repository.save(a);
//	}
//	
	public Admin findOneChecker(Long id) {
		return repository.findById(id).orElse(null);
	}

}
