package com.project.sellerapp.controller;

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
	
	@Autowired
	private TicketsService ticketsService;
	
	@RequestMapping(value = "/final-price", method = RequestMethod.PUT)
    public ResponseEntity<?> getTicketType(@RequestBody TicketsDTO ticketsDto) {
		Tickets tickets = new Tickets(ticketsDto);
		double price = ticketsService.getFinalPrice(tickets);
		System.out.println("Price: " + price);
        return new ResponseEntity(HttpStatus.OK);
    }
}
