package com.project.sellerapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sellerapp.dto.TicketsDTO;
import com.project.sellerapp.model.Tickets;
import com.project.sellerapp.service.TicketsService;

@RestController
@RequestMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketsController {
	
	private static Logger log = LoggerFactory.getLogger(TicketsController.class);
	private final TicketsService ticketsService;
	
	@Autowired
	public TicketsController(TicketsService ticketsService) {
		this.ticketsService = ticketsService;
	}


	@RequestMapping(value = "/final-price", method = RequestMethod.PUT)
    public double getTicketType(@RequestBody TicketsDTO ticketsDto) {
		
		Tickets tickets = new Tickets(ticketsDto);
		log.debug("Item request received for: " + tickets.getId());
		
		double price = ticketsService.getFinalPrice(tickets);
		System.out.println("Price: " + price);
        return price;
    }
}
