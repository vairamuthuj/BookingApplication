package com.book.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.book.model.Booking;
import com.book.model.Customer;
import com.book.model.Driver;
import com.book.model.InputRequest;
import com.book.model.Trip;
import com.book.service.DriverServiceImpl;

@RestController
@RequestMapping(value="/driver")
public class DriverController {

	private static Logger log = LogManager.getLogger(DriverController.class.getName());

	@Autowired
    private DriverServiceImpl driverService;
	
	@RequestMapping(value="register", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<Object> registerCustomer(@RequestBody Driver driver) {
		try {
			String response = driverService.registerUser(driver);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<Object> login(@RequestBody Customer customer){
		
		return new ResponseEntity<>("Successfully Logged-in", HttpStatus.OK);
	}
	
	@RequestMapping(value="validate", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<Object> validate(@RequestBody InputRequest inputRequest){
		try {
			String response = driverService.validate(inputRequest.getRequestValue(), inputRequest.getRequestType());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="completedHistory", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<?> getCompletedHistory(@RequestBody Integer driverId){
		try {
			List<Booking> bookings = driverService.getCompletedHistory(driverId);
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="plannedList", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<?> getPlannedList(@RequestBody Integer driverId){
		try {
			List<Booking> bookings = driverService.getPlannedList(driverId);
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="submitBid", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<Object> submitTrip(@RequestBody Trip trip){
		try {
			String response = driverService.submitTripBid(trip);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="bidList", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<?> getBidList(@RequestBody Integer driverId){
		
		try {
			List<Trip> bidList = driverService.getBidingList(driverId);
			return new ResponseEntity<>(bidList, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
