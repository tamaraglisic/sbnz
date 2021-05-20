package com.project.sellerapp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.project.sellerapp.dto.SkiResortDTO;

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
	
	

	public SkiResort() {
		super();
	}


	public SkiResort(SkiResortDTO sr) {
		super();
		
		
	}




	public SkiResort(Long id, String name, String description, String country, double liftPrice, double gondolaPrice,
			Date seasonStarts, Date seasonEnds, int groupCount, double ticketDeposit, double capacity) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.country = country;
		this.liftPrice = liftPrice;
		this.gondolaPrice = gondolaPrice;
		this.seasonStarts = seasonStarts;
		this.seasonEnds = seasonEnds;
		this.groupCount = groupCount;
		this.ticketDeposit = ticketDeposit;
		this.capacity = capacity;
	}
	
	

}
