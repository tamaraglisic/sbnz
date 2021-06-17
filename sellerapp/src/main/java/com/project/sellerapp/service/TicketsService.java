package com.project.sellerapp.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sellerapp.dto.TicketUserDTO;
import com.project.sellerapp.dto.TicketsDTO;
import com.project.sellerapp.model.SkiResort;
import com.project.sellerapp.model.TicketUser;
import com.project.sellerapp.model.Tickets;
import com.project.sellerapp.repository.SkiResortRepository;
import com.project.sellerapp.repository.TicketUsersRepository;
import com.project.sellerapp.repository.TicketsRepository;

@Service
public class TicketsService {
	
	private static Logger log = LoggerFactory.getLogger(TicketsService.class);
	private final KieContainer kieContainer;
	
	@Autowired
	private TicketsRepository ticketsRepository;
	@Autowired
	private SkiResortRepository skiResortRepository;
	@Autowired
	private TicketUsersRepository ticketUsersRepository;

	@Autowired
	public TicketsService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}

	public double getFinalPrice(TicketsDTO tickets) {
		System.out.println("Getting discount");
		System.out.println(new Date());
		KieSession kieSession = kieContainer.newKieSession("test-session");
		
		kieSession.getAgenda().getAgendaGroup("transport_type").setFocus();
		kieSession.insert(tickets);
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("using_period").setFocus();
		kieSession.insert(tickets);
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("user_type_discount").setFocus();
		kieSession.insert(tickets);
		kieSession.fireAllRules();
		
		
		kieSession.getAgenda().getAgendaGroup("period_discount").setFocus();
		kieSession.insert(tickets);
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("type_ticket").setFocus();
		kieSession.insert(tickets);
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieSession.insert(tickets);
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("type_ticket_discount").setFocus();
		kieSession.insert(tickets);
		kieSession.fireAllRules();
		
		if(tickets.isRegularGuest())
		{
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(tickets.getUsingEnd()); 
			cal.add(Calendar.DATE, 1);
			Date nextDay = cal.getTime();
			double occupacy = calculateOccupacy(nextDay, tickets.getSkiResort().getId());
			tickets.getSkiResort().setOccupacyForDay(nextDay);
			tickets.getSkiResort().setOccupacyRate(occupacy);
		}
		
		kieSession.dispose();
		
		//tickets.setBill(calculateBill(tickets));
		System.out.println(tickets);
		
		return tickets.getBill();
		
	}
	
	public double calculateOccupacy(Date forDate, Long skiResortId) {
		int sum = 0;
		List<Tickets> tickets = findTicketsByDate(forDate, skiResortId);
		for(Tickets t: tickets) {
			for(TicketUser tu: t.getTicketUsers()) {
				sum = sum + tu.getCount();
			}
		}
		SkiResort resort = skiResortRepository.findById(skiResortId).orElse(null);
		if(resort != null) {
			return 100*sum/resort.getCapacity();
		}
		
		return 0;
	}
	
	public List<Tickets> findTicketsByDate(Date forDate, Long skiResortId)
	{
		List<Tickets> tickets = ticketsRepository.findByDate(skiResortId, forDate);
		return tickets;
	}

	public Tickets create(TicketsDTO tickets) {
		Tickets t = new Tickets();

		SkiResort resort = skiResortRepository.findById(tickets.getSkiResort().getId()).orElse(null);
		t.setSkiResort(resort);
		t.setTypeTicket(tickets.getTypeTicket());
		t.setUsingPeriod(tickets.getUsingPeriod());
		t.setTransportType(tickets.getTransportType());
		t.setUsingStart(tickets.getUsingStart());
		t.setUsingEnd(tickets.getUsingEnd());
		t.setInitialPrice(tickets.getInitialPrice());
		Set<TicketUser> ticketUser = new HashSet<>();
		for(TicketUserDTO tu: tickets.getTicketUsers()) {
			TicketUser tentity = new TicketUser(tu.getUserType(), tu.getCount(), tu.getSingleTicketPrice());
			ticketUser.add(tentity);
		}
		t.setTicketUsers(ticketUser);
		t.setBill(tickets.getBill());
		t = ticketsRepository.save(t);
		return t;
	}
	
//	public List<Tickets> findMyTickets(Long id){
//		List<Tickets> retVal = ticketsRepository.findByRegisteredUserId(id);
//		return retVal;
//	}
	
//	public double calculateBill(TicketsDTO tickets) {
//		double bill = 0;
//		long days = Utility.getDays(tickets.getUsingStart(), tickets.getUsingEnd());
//		for(TicketUserDTO tu: tickets.getTicketUsers()) {
//			bill = bill + days*tu.getSingleTicketPrice()*tu.getCount();
//		}
//		return bill;
//	}
}
