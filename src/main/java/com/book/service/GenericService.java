package com.book.service;

import java.util.List;

public interface GenericService {

	public abstract String registerUser(Object object);
	public abstract String validate(String input, String type);	
	public abstract Object login(Object object);
	public abstract List<Object> getCompletedHistory(String ID);
	public abstract List<Object> getPlannedList(String ID);
	public abstract String submitTripBid(Object object);		
	public abstract List<Object > getBidingList(String ID);
	public abstract String acceptOffer(String ID);	
	public abstract String updateFeedback(Object object);
	public abstract Object getFeedback(String ID);

}
