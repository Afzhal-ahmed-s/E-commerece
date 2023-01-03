package com.eCommerce.Services;

import java.util.List;

import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.FeedBackException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.OrderException;
import com.eCommerce.model.Feedback;

public interface FeedBackService {

	public Feedback AddFeedBack(Feedback feedback,Integer orderId,Integer customerId,String key)
	throws LoginException,CustomerException,OrderException,FeedBackException;
	
	public Feedback updateFeedback(Feedback feedback,Integer customerId,String key) 
			throws LoginException,CustomerException,FeedBackException;
	
	public String deletefeedback(Integer feedbackId,Integer customerId,String key)
			throws LoginException,CustomerException,FeedBackException;
	
	public List<Feedback> viewAllFeedback(Integer customerId,String key)
			throws LoginException,CustomerException,FeedBackException;
	
	public Feedback viewFeedBackById(Integer feedbackId,Integer customerId,String key)
			throws LoginException,CustomerException,FeedBackException;
	
	
}
