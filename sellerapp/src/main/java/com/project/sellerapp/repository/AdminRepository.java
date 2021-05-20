package com.project.sellerapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.sellerapp.model.Admin;



@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

	Admin findByEmail(String email);
	
	@Query(value = "SELECT * FROM USERS WHERE TYPE = 'admin' AND ACTIVE = TRUE", nativeQuery = true)
	List<Admin> findAllAdmin();

	
}
