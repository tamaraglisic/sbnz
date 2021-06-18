package com.project.sellerapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.sellerapp.dto.SkiResortDTO;
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
	@Autowired
	private KieService kieService;
	
	public List<SkiResort> findAll(){
		return repository.findByActive(true);
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

	public SkiResort create(SkiResortDTO resort) {
		// TODO Auto-generated method stub
		SkiResort sr = new SkiResort();

		sr.setName(resort.getName());
		sr.setDescription(resort.getDescription());
		sr.setCountry(resort.getCountry());
		sr.setLiftPrice(resort.getLiftPrice());
		sr.setGondolaPrice(resort.getGondolaPrice());
		sr.setSeasonStarts(resort.getSeasonStarts());
		sr.setSeasonEnds(resort.getSeasonStarts());
		sr.setGroupCount(resort.getGroupCount());
		sr.setTicketDeposit(resort.getTicketDeposit());
		sr.setCapacity(resort.getCapacity());
		sr.setActive(true);
		try {
			sr = repository.save(sr);
		}
		catch(Exception e){
			return null;
		}
		
		return sr;
	}

	public SkiResort update(SkiResortDTO resort) {
		SkiResort sr = repository.findById(resort.getId()).orElse(null);
		if(sr != null) {
			sr.setName(resort.getName());
			sr.setDescription(resort.getDescription());
			sr.setCountry(resort.getCountry());
			sr.setLiftPrice(resort.getLiftPrice());
			sr.setGondolaPrice(resort.getGondolaPrice());
			sr.setSeasonStarts(resort.getSeasonStarts());
			sr.setSeasonEnds(resort.getSeasonStarts());
			sr.setGroupCount(resort.getGroupCount());
			sr.setTicketDeposit(resort.getTicketDeposit());
			sr.setCapacity(resort.getCapacity());
			try {
				sr = repository.save(sr);
			}
			catch(Exception e){
				return null;
			}
			return sr;
		}
		return null;
	}

	public SkiResort delete(Long id) {
		SkiResort created = repository.findById(id).orElse(null);
		if(created != null) {
			created.setActive(false);
			System.out.println("set to false");
			return repository.save(created);
		}
		return null;
	}

	public List<SkiResort> findByActive(boolean b) {
		// TODO Auto-generated method stub
		return repository.findByActive(true);
	}
	
	public List<SkiResort> findByName(String name){
		List<SkiResort> activeResorts = findByActive(true);
		List<SkiResort> retVal = new ArrayList<>();
		activeResorts.forEach(kieService.getRuleSession()::insert);
		QueryResults res = kieService.getRuleSession().getQueryResults("findResortByName", name);
	    for(QueryResultsRow row: res) {
	    	SkiResort resort = (SkiResort)row.get("resort");
	    	retVal.add(resort);
	    }
	    kieService.disposeRuleSession();
	    return retVal;
	     
	}
}
