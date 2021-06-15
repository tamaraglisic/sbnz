package com.project.sellerapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sellerapp.dto.Occupancy;
import com.project.sellerapp.dto.SkiResortDTO;
import com.project.sellerapp.helpers.Utility;
import com.project.sellerapp.model.SkiResort;
import com.project.sellerapp.service.SkiResortService;

@RestController
@RequestMapping(value = "/ski-resort", produces = MediaType.APPLICATION_JSON_VALUE)
public class SkiResortController {

	private static Logger log = LoggerFactory.getLogger(TicketsController.class);
	private final SkiResortService skiResortService;
	
	@Autowired
	public SkiResortController(SkiResortService skiResortService) {
		this.skiResortService = skiResortService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SkiResortDTO>> getAllResorts(){
		List<SkiResort> resorts = skiResortService.findAll();
		return new ResponseEntity<>(toDTOList(resorts), HttpStatus.OK);
	}
	
	@RequestMapping(value="/occupancy/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Occupancy>> getOccupancyForDays(@PathVariable Long id){
		List<Occupancy> retVal = new ArrayList<Occupancy>();
		Date forDay = new Date();
		for(int i =0; i<5; i++) {
			double percent = skiResortService.calculateOccupancy(forDay, id);
			Occupancy occ = new Occupancy(Utility.dateToString(forDay), percent);
			retVal.add(occ);
			forDay = Utility.getNextDay(forDay);
		}
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	private List<SkiResortDTO> toDTOList(List<SkiResort> list){
		List<SkiResortDTO> retVal = new ArrayList<SkiResortDTO>();
		for(SkiResort ski: list) {
			SkiResortDTO dto = new SkiResortDTO(ski);
			retVal.add(dto);
		}
		return retVal;
	}
}
