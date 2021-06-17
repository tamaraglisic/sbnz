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
	
	@Column(unique = false, nullable = false)
	private boolean active;
	

	public SkiResort() {
		super();
	}


	public SkiResort(SkiResortDTO sr) {
		
		this.id = sr.getId();
		this.name = sr.getName();
		this.description = sr.getDescription();
		this.country = sr.getCountry();
		this.liftPrice = sr.getLiftPrice();
		this.gondolaPrice = sr.getGondolaPrice();
		this.seasonStarts = sr.getSeasonStarts();
		this.seasonEnds = sr.getSeasonEnds();
		this.groupCount = sr.getGroupCount();
		this.ticketDeposit = sr.getTicketDeposit();
		this.capacity = sr.getCapacity();
		this.active = sr.isActive();
		
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


	public SkiResort(Long id, String name, String description, String country, double liftPrice, double gondolaPrice,
			Date seasonStarts, Date seasonEnds, int groupCount, double ticketDeposit, double capacity, boolean active) {
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
		this.active = active;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public double getLiftPrice() {
		return liftPrice;
	}


	public void setLiftPrice(double liftPrice) {
		this.liftPrice = liftPrice;
	}


	public double getGondolaPrice() {
		return gondolaPrice;
	}


	public void setGondolaPrice(double gondolaPrice) {
		this.gondolaPrice = gondolaPrice;
	}


	public Date getSeasonStarts() {
		return seasonStarts;
	}


	public void setSeasonStarts(Date seasonStarts) {
		this.seasonStarts = seasonStarts;
	}


	public Date getSeasonEnds() {
		return seasonEnds;
	}


	public void setSeasonEnds(Date seasonEnds) {
		this.seasonEnds = seasonEnds;
	}


	public int getGroupCount() {
		return groupCount;
	}


	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}


	public double getTicketDeposit() {
		return ticketDeposit;
	}


	public void setTicketDeposit(double ticketDeposit) {
		this.ticketDeposit = ticketDeposit;
	}


	public double getCapacity() {
		return capacity;
	}


	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
