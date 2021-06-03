package com.project.sellerapp.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sellerapp.dto.TicketsDTO;
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
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    ticketsDto.setUsingStart(date1);
		    ticketsDto.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		//Tickets tickets = new Tickets(ticketsDto);
		log.debug("Item request received for: " + ticketsDto.getId());
		
		double price = ticketsService.getFinalPrice(ticketsDto);
		System.out.println("Price: " + price);
        return price;
    }
	@RequestMapping(value = "/occupacy", method = RequestMethod.GET)
	public double calculateOccupacyRate() {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		String inputString2 = "28 05 2021";
		try {
		    Date date2 = myFormat.parse(inputString2);
		    Calendar cal = Calendar.getInstance(); 
			cal.setTime(date2); 
			cal.add(Calendar.DATE, 1);
			Date nextDay = cal.getTime();
			double occupacy = ticketsService.calculateOccupacy(nextDay, 1L);
			return occupacy;
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return 0;
		
	}
}
