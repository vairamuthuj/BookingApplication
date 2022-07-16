package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.model.Booking;
import com.book.model.Customer;
import com.book.model.Feedback;
import com.book.model.Trip;
import com.book.repository.CustomerRepository;
import com.book.util.CommonMethods;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
    private CustomerRepository customerRepository;
	
	@Override
	public String registerUser(Customer customer) {
		String response = customerRepository.registerCustomer(customer);
		return response;
	}

	@Override
	public String validate(String input, String type) {
		String response ="Please input valid Mobile Number.";
		if("mobile".equalsIgnoreCase(type)) {
			if(CommonMethods.isValidMobileNo(input)) {
				response = "Success";
			}
		}else if("email".equalsIgnoreCase(type)) {
			response ="Please input valid Email Address.";
			if(CommonMethods.isValidEmailAddress(input)) {
				response = "Success";
			}
		}
		return response;
	}

	@Override
	public Customer login(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getCompletedHistory(Integer customerId) {
		List<Booking> completedList = customerRepository.getCompletedHistory(customerId);
		return completedList;
	}

	@Override
	public List<Booking> getPlannedList(Integer customerId) {
		List<Booking> plannedList = customerRepository.getPlannedList(customerId);
		return plannedList;
	}

	@Override
	public String submitTripBid(Trip trip) {
		String response = customerRepository.submitTrip(trip);
		return response;
	}

	@Override
	public List<Trip> getBidingList(Integer customerId) {
		List<Trip> bidList = customerRepository.getBidingList(customerId, "get_bidding_list");
		return bidList;
	}

	@Override
	public String acceptOffer(Integer bidId) {
		//To update bid Table
		String response = customerRepository.update(bidId, "bid_update","Y");
		if("SUCCESS".equalsIgnoreCase(response)) {
			//Get accepted trip/bid details
			List<Trip> bidList = customerRepository.getBidingList(bidId, "get_accepted_bid");
			if(bidList != null && bidList.size() >0) {
				for(Trip trip:bidList) {
					//To convert trip into bookings
					response = customerRepository.submitBooking(trip);
					if("SUCCESS".equalsIgnoreCase(response)) {
						//update trip table as converted to booking
						response = customerRepository.update(trip.getTripId(), "trip_update","Y");
					}
				}
			}
			if("SUCCESS".equalsIgnoreCase(response)) {
				for(Trip trip:bidList) {
					response = customerRepository.delete(trip.getTripId(), "delete_bid");
				}
			}
		}else {
			response = customerRepository.update(bidId, "bid_update","N");
		}
		return "Bid Offer accepted successfully.";
	}

	@Override
	public String updateFeedback(Feedback feedback) {
		String response = customerRepository.submitFeedback(feedback);
		response = customerRepository.update(feedback.getBookingId(), "update_feedback","Y");
		
		return response;
	}

}
