package com.eCommerce.Services;

import java.util.List;

import com.eCommerce.exceptions.CardException;
import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.OrderException;
import com.eCommerce.exceptions.PaymentException;
import com.eCommerce.model.Payment;

public interface paymentServices {

	public Payment makePayment(Integer orderId,Integer cardId,Integer customerId,String key) 
			throws LoginException,CustomerException,OrderException,PaymentException,CardException;
	
	public Payment viewPaymentDetailsById(Integer paymentId,Integer customerId,String key)
			throws LoginException,CustomerException,OrderException,PaymentException;
	
	public List<Payment> getAllPaymentByCustomer(Integer customerId,String key)
			throws LoginException,CustomerException,OrderException,PaymentException;
	
	public String cancelPayment(Integer payId,Integer customerId,String key)
			throws LoginException,CustomerException,OrderException,PaymentException;
}
