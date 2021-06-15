package com.project.sellerapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sellerapp.model.SkiResort;
import com.project.sellerapp.model.TicketUser;
import com.project.sellerapp.model.Tickets;
import com.project.sellerapp.repository.SkiResortRepository;
import com.project.sellerapp.repository.TicketsRepository;

@Service
public class SkiResortService {

	@Autowired
	private SkiResortRepository repository;
	
	@Autowired
	private TicketsRepository ticketsRepository;
	
	public List<SkiResort> findAll(){
		return repository.findAll();
	}
	
	public SkiResort findById(Long id) {
		SkiResort ski = repository.findById(id).orElse(null);
		return ski;
	}
	
	public double calculateOccupancy(Date forDate, Long skiResortId) {
		int sum = 0;
		List<Tickets> tickets = findTicketsByDate(forDate, skiResortId);
		for(Tickets t: tickets) {
			for(TicketUser tu: t.getTicketUsers()) {
				sum = sum + tu.getCount();
			}
		}
		SkiResort resort = repository.findById(skiResortId).orElse(null);
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
}
