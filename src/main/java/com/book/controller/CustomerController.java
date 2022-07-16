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
import com.book.model.Feedback;
import com.book.model.InputRequest;
import com.book.model.Trip;
import com.book.service.CustomerServiceImpl;

@RestController
@RequestMapping(value="/customer")
public class CustomerController {
	
	private static Logger log = LogManager.getLogger(CustomerController.class.getName());
	
	@Autowired
    private CustomerServiceImpl customerService;

	/*@RequestMapping(value="testing", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<Object> testing(@RequestBody Integer customerId){
		System.out.println("customerID:::"+customerId);
		return new ResponseEntity<>("Successfully Logged-in", HttpStatus.OK);
	}
	*/
	
	@RequestMapping(value="register", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<Object> registerCustomer(@RequestBody Customer customer) {
		try {
			String response = customerService.registerUser(customer);
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
			String response = customerService.validate(inputRequest.getRequestValue(), inputRequest.getRequestType());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="completedHistory", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<?> getCompletedHistory(@RequestBody Integer customerId){
		try {
			List<Booking> bookings = customerService.getCompletedHistory(customerId);
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="plannedList", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<?> getPlannedList(@RequestBody Integer customerId){
		try {
			List<Booking> bookings = customerService.getPlannedList(customerId);
			return new ResponseEntity<>(bookings, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="submitTrip", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<Object> submitTrip(@RequestBody Trip trip){
		try {
			String response = customerService.submitTripBid(trip);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value="bidList", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<?> getBidList(@RequestBody Integer customerId){
		
		try {
			List<Trip> bidList = customerService.getBidingList(customerId);
			return new ResponseEntity<>(bidList, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="updateFeedback", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<?> updateFeedback(@RequestBody Feedback feedback){
		
		try {
			String response = customerService.updateFeedback(feedback);
			if("SUCCESS".equalsIgnoreCase(response)) {
				response = "Feedback update is successful";
			}else {
				response = "Feedback update is not successful, Please try again later.";
			}
			return new ResponseEntity<>("Feedback updated successfully", HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="acceptOffer", method=RequestMethod.POST, produces= {"application/json"})
	ResponseEntity<?> acceptOffer(@RequestBody Integer bidId){
		try {
			String response = customerService.acceptOffer(bidId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception ex){
			log.error(ex.getMessage(), ex);
			return new ResponseEntity<>("Error in processing Request "+ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
