package com.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.model.Booking;
import com.book.model.Driver;
import com.book.model.Trip;
import com.book.repository.DriverRepository;
import com.book.util.CommonMethods;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
    private DriverRepository driverRepository;
	
	@Override
	public String registerUser(Driver driver) {
		String response = driverRepository.registerDriver(driver);
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
	public Driver login(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> getCompletedHistory(Integer driverId) {
		List<Booking> completedList = driverRepository.getCompletedHistory(driverId);
		return completedList;
	}

	@Override
	public List<Booking> getPlannedList(Integer driverId) {
		List<Booking> plannedList = driverRepository.getPlannedList(driverId);
		return plannedList;
	}

	@Override
	public String submitTripBid(Trip trip) {
		String response = driverRepository.submitBid(trip);
		return response;
	}

	@Override
	public List<Trip> getBidingList(Integer driverId) {
		List<Trip> bidList = driverRepository.getBidingList(driverId, "driver_get_bidding_list");
		return bidList;
	}

	@Override
	public Object getFeedback(Integer driverId) {
		// TODO Auto-generated method stub
		return null;
	}


}
