package com.project.sellerapp.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.project.sellerapp.model.SkiResort;
import com.project.sellerapp.model.TicketUser;

public class TicketsDTO {

	private Long id;
	private SkiResortDTO skiResort;
	private String typeTicket; // porodicna, grupna, pojedinacna
	private String usingPeriod; // dnevna, poludnevna, nocn
	private String transportType; // zicara, gondola, zicara+gondola
	private Date usingStart;
	private Date usingEnd;
	private double initialPrice;
	private Set<TicketUserDTO> ticketUsers; // TicketUser, count
	private double bill;
	
	
	public TicketsDTO() {
		super();
	}

	public TicketsDTO(Long id, SkiResortDTO skiResort, String typeTicket, String usingPeriod, String transportType, Date usingStart,
			Date usingEnd, double initialPrice, Set<TicketUserDTO> ticketUsers, double bill) {
		super();
		this.id = id;
		this.skiResort = skiResort;
		this.typeTicket = typeTicket;
		this.usingPeriod = usingPeriod;
		this.transportType = transportType;
		this.usingStart = usingStart;
		this.usingEnd = usingEnd;
		this.initialPrice = initialPrice;
		this.ticketUsers = ticketUsers;
		this.bill = bill;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SkiResortDTO getSkiResort() {
		return skiResort;
	}

	public void setSkiResort(SkiResortDTO skiResort) {
		this.skiResort = skiResort;
	}

	public String getTypeTicket() {
		return typeTicket;
	}

	public void setTypeTicket(String typeTicket) {
		this.typeTicket = typeTicket;
	}

	public String getUsingPeriod() {
		return usingPeriod;
	}

	public void setUsingPeriod(String usingPeriod) {
		this.usingPeriod = usingPeriod;
	}

	public String getTransportType() {
		return transportType;
	}

	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	public Date getUsingStart() {
		return usingStart;
	}

	public void setUsingStart(Date usingStart) {
		this.usingStart = usingStart;
	}

	public Date getUsingEnd() {
		return usingEnd;
	}

	public void setUsingEnd(Date usingEnd) {
		this.usingEnd = usingEnd;
	}

	public double getInitialPrice() {
		return initialPrice;
	}

	public void setInitialPrice(double initialPrice) {
		this.initialPrice = initialPrice;
	}

	public Set<TicketUserDTO> getTicketUsers() {
		return ticketUsers;
	}

	public void setTicketUsers(Set<TicketUserDTO> ticketUsers) {
		this.ticketUsers = ticketUsers;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}
	
	public int getNumberOfUsers() {
		int count = 0;
		for (TicketUserDTO tu: this.ticketUsers) {
			count = count + tu.getCount();
		}
		return count;
	}
	
	public void addDiscount(double percent) {
		this.bill = this.bill*(100-percent)/100;
	}
	
	public void increasePrice(double percent) {
		this.bill = this.bill*(100+percent)/100;
	}
	
	public int getUsersCount(String userType) {
		int retVal = 0;
		for(TicketUserDTO tu: this.ticketUsers) {
			if(tu.getUserType().equals(userType))
				retVal = tu.getCount();
		}
		return retVal;
	}
	
}
