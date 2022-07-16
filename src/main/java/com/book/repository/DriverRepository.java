package com.book.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.book.model.Booking;
import com.book.model.Driver;
import com.book.model.Trip;
import com.book.util.PropertyUtil;

@Repository
public class DriverRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;

	public String registerDriver(Driver driver) {
		String response = null;
		int status = jdbcTemplate.update(PropertyUtil.getProperty("insert_driver"), driver.getFirstName(),
				driver.getLastName(),driver.getMobileNumber(),driver.getEmailAddress(), driver.getAdhaarCardNumber(), 
				driver.getDrivingLicense(), driver.getIsAgency() != null ? driver.getIsAgency():"N"); 
		
	    if(status != 0){
	    	response = "Driver registration is successful.";
	    }else {
	    	response = "Driver registration is not successful, please try again later";
	    }
		return response;
	}
	
	public List<Booking> getCompletedHistory(Integer driverId) {
		
		String query = PropertyUtil.getProperty("driver_completed_history");
		query = query.replaceAll("INPUTID", String.valueOf(driverId));
		return jdbcTemplate.query(query, new RowMapper<Booking>() {
	        @Override
	        public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
	        	Booking booking = new Booking();
	        	booking.setBookingId(resultSet.getInt("booking_id"));
	        	booking.setCustomerId(resultSet.getInt("customer_id"));
	        	booking.setDriverId(resultSet.getInt("driver_id"));
	        	booking.setFromLocation(resultSet.getString("from_location"));
	        	booking.setToLocation(resultSet.getString("to_location"));
	        	booking.setFromDate(resultSet.getString("from_date"));
	        	booking.setToDate(resultSet.getString("to_date"));
	        	booking.setTripType(resultSet.getString("trip_type"));
	        	booking.setVehicleType(resultSet.getString("vehicle_type"));
	        	booking.setStatus(resultSet.getString("status"));
	        	booking.setActionBy(resultSet.getString("action_by"));
	        	booking.setBidAmount(resultSet.getString("bid_amount"));
	        	booking.setAmountPaid(resultSet.getString("paid_amount"));
	        	booking.setFeedbackProvided(resultSet.getString("feedback_provided"));
	            return booking;
	        }
	    });
	    
	}
	
	public List<Booking> getPlannedList(Integer driverId) {
		
		String query = PropertyUtil.getProperty("driver_planned_list");
		query = query.replaceAll("INPUTID", String.valueOf(driverId));
		return jdbcTemplate.query(query, new RowMapper<Booking>() {
	        @Override
	        public Booking mapRow(ResultSet resultSet, int i) throws SQLException {
	        	Booking booking = new Booking();
	        	booking.setBookingId(resultSet.getInt("booking_id"));
	        	booking.setCustomerId(resultSet.getInt("customer_id"));
	        	booking.setDriverId(resultSet.getInt("driver_id"));
	        	booking.setFromLocation(resultSet.getString("from_location"));
	        	booking.setToLocation(resultSet.getString("to_location"));
	        	booking.setFromDate(resultSet.getString("from_date"));
	        	booking.setToDate(resultSet.getString("to_date"));
	        	booking.setTripType(resultSet.getString("trip_type"));
	        	booking.setVehicleType(resultSet.getString("vehicle_type"));
	        	booking.setStatus(resultSet.getString("status"));
	        	booking.setActionBy(resultSet.getString("action_by"));
	        	booking.setBidAmount(resultSet.getString("bid_amount"));
	        	booking.setAmountPaid(resultSet.getString("paid_amount"));
	        	booking.setFeedbackProvided(resultSet.getString("feedback_provided"));
	            return booking;
	        }
	    });
	    
	}

	public String submitBid(Trip trip) {
		String response = null;
		
		int status = jdbcTemplate.update(PropertyUtil.getProperty("insert_bid"), trip.getTripId(), trip.getDriverId(), trip.getAmount()); 
		
	    if(status != 0){
	    	response = "Your Bid sumbitted successfully.";
	    }else {
	    	response = "Your Bid is not sumbitted, please try again later.";
	    }
			    
		return response;
	}
	
	public List<Trip> getBidingList(Integer inputId, String inputType) {
		
		String query = PropertyUtil.getProperty(inputType);
		query = query.replaceAll("INPUTID", String.valueOf(inputId));
		
		return jdbcTemplate.query(query, new RowMapper<Trip>() {
	        @Override
	        public Trip mapRow(ResultSet resultSet, int i) throws SQLException {
	        	Trip trip = new Trip();
	        	trip.setTripId(resultSet.getInt("trip_id"));
	        	trip.setCustomerId(resultSet.getInt("customer_id"));
	        	//trip.setDriverId(resultSet.getInt("driver_id"));
	        	trip.setFromLocation(resultSet.getString("from_location"));
	        	trip.setToLocation(resultSet.getString("to_location"));
	        	trip.setFromDate(resultSet.getString("from_date"));
	        	trip.setStartTime(resultSet.getString("start_time"));
	        	trip.setToDate(resultSet.getString("to_date"));
	        	trip.setTripType(resultSet.getString("trip_type"));
	        	trip.setVehicleType(resultSet.getString("vehicle_type"));
	        	//trip.setBidId(resultSet.getInt("bid_id"));
	        	//trip.setAmount(resultSet.getString("amount"));
	        	trip.setConvertedToBooking(resultSet.getString("converted_to_booking"));
	        	//trip.setAccepted(resultSet.getString("accepted"));
	            return trip;
	        }
	    });
	}

}
