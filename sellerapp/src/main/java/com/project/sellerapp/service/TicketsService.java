package com.project.sellerapp.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sellerapp.dto.TicketsDTO;

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
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.insert(tickets);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return tickets.getBill();
		
	}
}
