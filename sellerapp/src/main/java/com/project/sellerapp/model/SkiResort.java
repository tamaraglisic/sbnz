package com.project.sellerapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SkiResort {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String name;
	
	@Column(unique = false, nullable = false)
	private String description;
	
	@Column(unique = false, nullable = false)
	private String country;
	
	@Column(unique = false, nullable = false)
	private double liftPrice;
	
	@Column(unique = false, nullable = false)
	private double gondolaPrice;
	
	@Column(unique = false, nullable = false)
	private Date seasonStarts;
	
	@Column(unique = false, nullable = false)
	private Date seasonEnds;
	
	@Column(unique = false, nullable = false)
	private int groupCount;
	
	@Column(unique = false, nullable = false)
	private double ticketDeposit;
	
	@Column(unique = false, nullable = false)
	private double capacity;

}
