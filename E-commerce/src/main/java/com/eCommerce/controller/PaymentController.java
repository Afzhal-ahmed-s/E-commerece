package com.eCommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.Services.paymentServices;
import com.eCommerce.exceptions.CardException;
import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.OrderException;
import com.eCommerce.exceptions.PaymentException;
import com.eCommerce.model.Payment;

@RestController
public class PaymentController {

	@Autowired
	private paymentServices pservice;
	
	@PostMapping("/makePayments")
	public ResponseEntity<Payment> MakePaymentHandler(@RequestParam Integer orderId,@RequestParam Integer cardId,@RequestParam Integer customerId,@RequestParam String key)
			throws LoginException, CustomerException, OrderException, PaymentException, CardException{
		
		Payment pay = pservice.makePayment(orderId, cardId, customerId, key);
		
		return new ResponseEntity<Payment>(pay,HttpStatus.OK);
	}
	
	@GetMapping("/viewPayment/{paymentId}")
	public ResponseEntity<Payment> viewPaymentDetailsHandler(@PathVariable Integer paymentId,@RequestParam Integer customerId,@RequestParam String key)
			throws LoginException, CustomerException, OrderException, PaymentException {
		
		Payment pay = pservice.viewPaymentDetailsById(paymentId, customerId, key);
		
		return new ResponseEntity<Payment>(pay,HttpStatus.OK);
	}
	
	@GetMapping("/viewAllPayments")
	public ResponseEntity<List<Payment>> viewAllPaymentDetailsHandler(@RequestParam Integer customerId,@RequestParam String key)
			throws LoginException, CustomerException, OrderException, PaymentException {
		
		List<Payment> pay = pservice.getAllPaymentByCustomer(customerId, key);
		
		return new ResponseEntity<List<Payment>>(pay,HttpStatus.OK);
	}
	
	
	public ResponseEntity<String> cancelPaymentHandler(@PathVariable Integer paymentId,@RequestParam Integer customerId,@RequestParam String key)
			throws LoginException, CustomerException, OrderException, PaymentException {
		
		String message = pservice.cancelPayment(paymentId, customerId, key);
		
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
}
