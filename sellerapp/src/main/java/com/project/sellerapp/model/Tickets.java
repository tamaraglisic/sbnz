package com.project.sellerapp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Tickets {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ski_resort_id", nullable = true)
	private SkiResort skiResort;

	@Column(unique = false, nullable = false)
	private String typeTicket; // dnevna, poludnevna, nocna
	
	@Column(unique = false, nullable = false)
	private String transportType; // zicara, gondola, zicara+gondola
	
	@Column(unique = false, nullable = false)
	private Date usingStart;
	
	@Column(unique = false, nullable = false)
	private Date usingEnd;
	
	@Column(unique = false, nullable = false)
	private double initialPrice;

	@OneToMany(fetch = FetchType.EAGER)
//	@JoinColumn(name = "ticket_id", nullable = true)
	private Set<TicketUser> ticketUsers; // TicketUser, count
	
	@Column(unique = false, nullable = false)
	private double bill;

}
