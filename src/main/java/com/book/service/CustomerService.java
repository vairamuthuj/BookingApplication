package com.book.service;

import java.util.List;

import com.book.model.Bid;
import com.book.model.Booking;
import com.book.model.Customer;
import com.book.model.Feedback;
import com.book.model.Trip;

public interface CustomerService {

	public abstract String registerUser(Customer customer);
	public abstract String validate(String input, String type);	
	public abstract Customer login(Object object);
	public abstract List<Booking> getCompletedHistory(Integer customerId);
	public abstract List<Booking> getPlannedList(Integer customerId);
	public abstract String submitTripBid(Trip trip);		
	public abstract List<Trip> getBidingList(Integer customerId);
	public abstract String acceptOffer(Integer bidId);	
	public abstract String updateFeedback(Feedback feedback);

}
