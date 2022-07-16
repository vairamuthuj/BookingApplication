package com.book.service;

import java.util.List;

import com.book.model.Bid;
import com.book.model.Booking;
import com.book.model.Driver;
import com.book.model.Trip;

public interface DriverService {

	public abstract String registerUser(Driver driver);
	public abstract String validate(String input, String type);	
	public abstract Driver login(Object object);
	public abstract List<Booking> getCompletedHistory(Integer driverId);
	public abstract List<Booking> getPlannedList(Integer driverId);
	public abstract List<Trip> getBidingList(Integer driverId);
	public abstract Object getFeedback(Integer driverId);
	public abstract String submitTripBid(Trip trip);

}
