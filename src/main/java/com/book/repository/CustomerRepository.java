package com.book.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.book.model.Booking;
import com.book.model.Customer;
import com.book.model.Feedback;
import com.book.model.Trip;
import com.book.util.CommonMethods;
import com.book.util.PropertyUtil;

@Repository
public class CustomerRepository {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	public List<Booking> getCompletedHistory(Integer customerId) {
		
		String query = PropertyUtil.getProperty("customer_completed_history");
		query = query.replaceAll("INPUTID", String.valueOf(customerId));
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
	
	public List<Booking> getPlannedList(Integer customerId) {
		
		String query = PropertyUtil.getProperty("customer_planned_list");
		query = query.replaceAll("INPUTID", String.valueOf(customerId));
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
	
	public String submitTrip(Trip trip) {
		String response = null;
		
		/*DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date myDate = null;
		try {
			myDate = formatter.parse(trip.getFromDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
		*/
		int status = jdbcTemplate.update(PropertyUtil.getProperty("insert_trip"), trip.getCustomerId(), trip.getFromLocation(), 
				trip.getToLocation(),trip.getFromDate(),trip.getStartTime(),(trip.getToDate() != null ? trip.getToDate():trip.getFromDate()),trip.getTripType(),trip.getVehicleType()); 
		
	    if(status != 0){
	    	response = "Your Trip plan sumbitted successfully.";
	    }else {
	    	response = "Your Trip plan is not sumbitted, please try again later.";
	    }
			    
		return response;
	}
	
	public String registerCustomer(Customer customer) {
		String response = null;
		int status = jdbcTemplate.update(PropertyUtil.getProperty("insert_customer"), customer.getFirstName(),customer.getLastName(),customer.getMobileNumber(),customer.getEmailAddress()); 
		
	    if(status != 0){
	    	response = "Customer registration is successful.";
	    }else {
	    	response = "Customer registration is not successful, please try again later";
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
	        	trip.setDriverId(resultSet.getInt("driver_id"));
	        	trip.setFromLocation(resultSet.getString("from_location"));
	        	trip.setToLocation(resultSet.getString("to_location"));
	        	trip.setFromDate(resultSet.getString("from_date"));
	        	trip.setStartTime(resultSet.getString("start_time"));
	        	trip.setToDate(resultSet.getString("to_date"));
	        	trip.setTripType(resultSet.getString("trip_type"));
	        	trip.setVehicleType(resultSet.getString("vehicle_type"));
	        	trip.setBidId(resultSet.getInt("bid_id"));
	        	trip.setAmount(resultSet.getString("amount"));
	        	trip.setConvertedToBooking(resultSet.getString("converted_to_booking"));
	        	trip.setAccepted(resultSet.getString("accepted"));
	            return trip;
	        }
	    });
	}
	
	public String update(Integer inputId, String inputType, String value) {
		
		int status = jdbcTemplate.update(PropertyUtil.getProperty(inputType), value, inputId); 
			    
		return CommonMethods.returnMessages(status);
	}
	
	public String delete(Integer inputId, String inputType) {
		
		int status = jdbcTemplate.update(PropertyUtil.getProperty(inputType), inputId); 
			    
		return CommonMethods.returnMessages(status);
	}
	

	public String submitBooking(Trip trip) {
		
		int status = jdbcTemplate.update(PropertyUtil.getProperty("insert_booking"), trip.getCustomerId(), trip.getDriverId(), trip.getFromLocation(), 
				trip.getToLocation(),trip.getFromDate(),trip.getStartTime(),(trip.getToDate() != null ? trip.getToDate():trip.getFromDate()),trip.getTripType(),
				trip.getVehicleType(),"IN_PROGRESS",trip.getAmount()); 
		
		return CommonMethods.returnMessages(status);
	}
	
	public String submitFeedback(Feedback feedback) {
		
		int status = jdbcTemplate.update(PropertyUtil.getProperty("insert_feedback"), feedback.getBookingId(), feedback.getDriverId(), feedback.getRating(), feedback.getComments()); 
		
		return CommonMethods.returnMessages(status);
	}
	
	/*
	public List<Customer> getCustomer() throws SQLException {
		
		return jdbcTemplate.query("select customer_id,first_name,last_name,mobile_no, email_address,status from customers", new RowMapper<Customer>() {
	        @Override
	        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
	        	Customer customer = new Customer();
	        	System.out.println(resultSet.getInt("customer_id"));
				System.out.println(resultSet.getString("first_name"));
				System.out.println(resultSet.getString("mobile_no"));
	        	customer.setCustomerId(resultSet.getInt("customer_id"));
	        	customer.setFirstName(resultSet.getString("first_name"));
	        	customer.setLastName(resultSet.getString("last_name"));
	        	customer.setMobileNumber(resultSet.getString("mobile_no"));
	        	customer.setEmailAddress(resultSet.getString("email_address"));
	            return customer;
	        }
	    });
		
	}*/
}
