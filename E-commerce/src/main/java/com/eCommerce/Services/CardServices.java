package com.eCommerce.Services;

import java.util.List;

import com.eCommerce.exceptions.CardException;
import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.model.CardDetails;

public interface CardServices {

	public CardDetails addcard(CardDetails card,String key,Integer customerId) 
			throws CardException,LoginException,CustomerException;
	
	public String deleteCard(Integer cardId,String Key,Integer customerId)
			throws CardException,LoginException,CustomerException;
	
	public CardDetails getCardByCardId(Integer cardId,String key, Integer customerId)
			throws CardException,LoginException,CustomerException;
	
	public List<CardDetails> getAllCardByCustomerId(String key, Integer customerId)
			throws CardException,LoginException,CustomerException;
	
}
