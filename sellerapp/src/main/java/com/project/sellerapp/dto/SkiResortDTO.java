package com.project.sellerapp.dto;

import java.util.Date;

import com.project.sellerapp.model.SkiResort;


public class SkiResortDTO {

	private Long id;
	private String name;
	private String description;
	private String country;
	private double liftPrice;
	private double gondolaPrice;
	private Date seasonStarts;
	private Date seasonEnds;
	private int groupCount;
	private double ticketDeposit;
	private double capacity;
	private boolean active;
	
	private Date occupacyForDay;
	private double occupacyRate;
	
	public SkiResortDTO() {
		super();
	}
	public SkiResortDTO(SkiResort ski) {
		super();
		this.id = ski.getId();
		this.name = ski.getName();
		this.description = ski.getDescription();
		this.country = ski.getCountry();
		this.liftPrice = ski.getLiftPrice();
		this.gondolaPrice = ski.getGondolaPrice();
		this.seasonStarts = ski.getSeasonStarts();
		this.seasonEnds = ski.getSeasonEnds();
		this.groupCount = ski.getGroupCount();
		this.ticketDeposit = ski.getTicketDeposit();
		this.capacity = ski.getCapacity();
		this.active = ski.isActive();
	}

	public SkiResortDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public SkiResortDTO(Long id, String name, String description, String country, double liftPrice, double gondolaPrice,
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

	public SkiResortDTO(Long id, String name, String description, String country, double liftPrice, double gondolaPrice,
			Date seasonStarts, Date seasonEnds, int groupCount, double ticketDeposit, double capacity,
			Date occupacyForDay, double occupacyRate) {
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
		this.occupacyForDay = occupacyForDay;
		this.occupacyRate = occupacyRate;
	}

	public SkiResortDTO(Long id, String name, String description, String country, double liftPrice, double gondolaPrice,
			Date seasonStarts, Date seasonEnds, int groupCount, double ticketDeposit, double capacity, boolean active,
			Date occupacyForDay, double occupacyRate) {
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
		this.occupacyForDay = occupacyForDay;
		this.occupacyRate = occupacyRate;
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

	public Date getOccupacyForDay() {
		return occupacyForDay;
	}

	public void setOccupacyForDay(Date occupacyForDay) {
		this.occupacyForDay = occupacyForDay;
	}

	public double getOccupacyRate() {
		return occupacyRate;
	}

	public void setOccupacyRate(double occupacyRate) {
		this.occupacyRate = occupacyRate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

}
