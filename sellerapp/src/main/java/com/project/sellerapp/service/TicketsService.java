package com.project.sellerapp.service;

import java.util.Date;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sellerapp.dto.TicketUserDTO;
import com.project.sellerapp.dto.TicketsDTO;
import com.project.sellerapp.helpers.Utility;

@Service
public class TicketsService {
	
	private static Logger log = LoggerFactory.getLogger(TicketsService.class);
	private final KieContainer kieContainer;

	@Autowired
	public TicketsService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}

	public double getFinalPrice(TicketsDTO tickets) {
		System.out.println("Getting discount");
		System.out.println(new Date());
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.insert(tickets);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		tickets.setBill(calculateBill(tickets));
		System.out.println(tickets);
		
		return tickets.getBill();
		
	}
	
	public double calculateBill(TicketsDTO tickets) {
		double bill = 0;
		long days = Utility.getDays(tickets.getUsingStart(), tickets.getUsingEnd());
		for(TicketUserDTO tu: tickets.getTicketUsers()) {
			bill = bill + days*tu.getSingleTicketPrice()*tu.getCount();
		}
		return bill;
	}
}
