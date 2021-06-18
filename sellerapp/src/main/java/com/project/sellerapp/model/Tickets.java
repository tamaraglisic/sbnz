package com.project.sellerapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.project.sellerapp.dto.TicketUserDTO;
import com.project.sellerapp.dto.TicketsDTO;

@Entity
public class Tickets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ski_resort_id", nullable = true)
	private SkiResort skiResort;

	@Column(unique = false, nullable = false)
	private String typeTicket; // porodicna, grupna, pojedinacna
	
	@Column(unique = false, nullable = false)
	private String usingPeriod; // dnevna, poludnevna, nocna
	
	@Column(unique = false, nullable = true)
	private String transportType; // zicara, gondola, zicara+gondola
	
	@Column(unique = false, nullable = true)
	private Date usingStart;
	
	@Column(unique = false, nullable = true)
	private Date usingEnd;
	
	@Column(unique = false, nullable = true)
	private double initialPrice;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//	@JoinColumn(name = "ticket_id", nullable = true)
	private Set<TicketUser> ticketUsers; // TicketUser, count
	
	@Column(unique = false, nullable = false)
	private double bill;

	
	
	public Tickets() {
		super();
	}
	
	public Tickets(TicketsDTO t) {
		this.id = t.getId();
		this.skiResort = new SkiResort(t.getSkiResort());
		this.typeTicket = t.getTypeTicket();
		this.usingPeriod = t.getUsingPeriod();
		this.transportType = t.getTransportType();
		this.usingStart = t.getUsingStart();
		this.usingEnd = t.getUsingEnd();
		this.initialPrice = t.getInitialPrice();
		this.ticketUsers = ticketUsersToEntity(t.getTicketUsers());
		this.bill = t.getBill();
	}
	
	private Set<TicketUser> ticketUsersToEntity(Set<TicketUserDTO> set){
		Set<TicketUser> retVal = new HashSet<TicketUser>();
		if(set == null)
			return retVal;
		for(TicketUserDTO t: set) {
			retVal.add(new TicketUser(t));
		}
		return retVal;
	}
	public Tickets(Long id, SkiResort skiResort, String typeTicket, String usingPeriod, String transportType, Date usingStart,
			Date usingEnd, double initialPrice, Set<TicketUser> ticketUsers, double bill) {
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

	public SkiResort getSkiResort() {
		return skiResort;
	}

	public void setSkiResort(SkiResort skiResort) {
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

	public Set<TicketUser> getTicketUsers() {
		return ticketUsers;
	}

	public void setTicketUsers(Set<TicketUser> ticketUsers) {
		this.ticketUsers = ticketUsers;
	}

	public double getBill() {
		return bill;
	}

	public void setBill(double bill) {
		this.bill = bill;
	}
	
	

}
