package com.project.sellerapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sellerapp.dto.TicketsDTO;
import com.project.sellerapp.model.RegisteredUser;
import com.project.sellerapp.model.Tickets;
import com.project.sellerapp.model.User;
import com.project.sellerapp.service.RegisteredUserService;
import com.project.sellerapp.service.TicketsService;



@RestController
@RequestMapping(value = "/tickets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TicketsController {
	
	private static Logger log = LoggerFactory.getLogger(TicketsController.class);
	private final TicketsService ticketsService;
	private final RegisteredUserService registeredUserService;
	
	@Autowired
	public TicketsController(TicketsService ticketsService, RegisteredUserService regService) {
		this.ticketsService = ticketsService;
		this.registeredUserService = regService;
	}


	@RequestMapping(value = "/final-price", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('REGISTERED_USER')")
    public ResponseEntity<TicketsDTO> getTicketType(@RequestBody TicketsDTO ticketsDto) {
		System.out.println(ticketsDto);
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
        return new ResponseEntity<>(ticketsDto, HttpStatus.OK);
    }
	
	@RequestMapping(value="/my-tickets", method = RequestMethod.GET)
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public ResponseEntity<List<TicketsDTO>> getMyTickets(){
		RegisteredUser registeredUser;

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = ((User) currentUser.getPrincipal()).getEmail();
        registeredUser = registeredUserService.findByEmail(username);
      //  List<Tickets> tickets = ticketsService.findMyTickets(registeredUser.getId());
        List<TicketsDTO> retVal = toDtoList(registeredUser.getTickets());
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('REGISTERED_USER')")
	public ResponseEntity<TicketsDTO> createTickets(@RequestBody TicketsDTO tickets){
		RegisteredUser registeredUser;

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(currentUser);
        String username = ((User) currentUser.getPrincipal()).getEmail();
        registeredUser = registeredUserService.findByEmail(username);
        
		Tickets created = ticketsService.create(tickets);
		registeredUser.getTickets().add(created);
		registeredUserService.save(registeredUser);
		
		return new ResponseEntity<>(new TicketsDTO(created), HttpStatus.OK);
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
	
	private List<TicketsDTO> toDtoList(Set<Tickets> set){
		List<TicketsDTO> retVal = new ArrayList<TicketsDTO>();
		for(Tickets t: set) {
			TicketsDTO dto = new TicketsDTO(t);
			retVal.add(dto);
		}
		return retVal;
	}
}
