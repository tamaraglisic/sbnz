package com.project.sellerapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.sellerapp.model.SkiResort;

@Repository
public interface SkiResortRepository extends JpaRepository<SkiResort, Long>{

	
	List<SkiResort> findAll();
	
	Optional<SkiResort> findById(Long id);

	List<SkiResort> findByActive(boolean b);
	
	
}
