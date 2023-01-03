package com.eCommerce.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eCommerce.exceptions.CardException;
import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.OrderException;
import com.eCommerce.exceptions.PaymentException;
import com.eCommerce.model.CardDetails;
import com.eCommerce.model.CurrentUserSession;
import com.eCommerce.model.Customer;
import com.eCommerce.model.Order;
import com.eCommerce.model.Payment;
import com.eCommerce.repo.CurrentUserSessionRepo;
import com.eCommerce.repo.CustomerRepo;
import com.eCommerce.repo.OrderRepo;
import com.eCommerce.repo.PaymentRepo;

@Service
public class PaymentServicesImpl implements paymentServices{

	@Autowired
	private PaymentRepo prepo;
	
	@Autowired
	private CustomerRepo crepo;
	
	@Autowired
	private CurrentUserSessionRepo cusrepo;
	
	@Autowired
	private OrderRepo orepo;
	
	@Autowired
	private CardServices cardService;
	
	
	@Override
	public Payment makePayment(Integer orderId,Integer cardId, Integer customerId, String key)
			throws LoginException, CustomerException, OrderException, PaymentException, CardException {
		Customer customer = checkLogin(key, customerId);
		
		CardDetails card = cardService.getCardByCardId(cardId, key, customerId);
		
		 if(card==null) throw new CardException("No card Found With Id: "+cardId);
		
		Optional<Order> opt = orepo.findById(orderId);
		
		if(opt.isEmpty()) throw new OrderException("Invalid OrderId");
		
		Order order = opt.get();
		
		if(order.getPayment()!=null||order.getOrderStatus().equals("Order Cancelled")) {
			throw new OrderException("Order Has been Cancelled or Payment already done");
		}
		
		Payment payment=new Payment();
		payment.setPaymentAmount((int)order.getOrderAmount());
		payment.setPaymentStatus(true);
		payment.setLocalDate(LocalDate.now());
		payment.setOrder(order);
		payment.setCard(card);
		
		Payment savedPayment = prepo.save(payment);
		
		order.setPayment(savedPayment);
		order.setOrderStatus("Order Placed");
		orepo.save(order);
		
		return savedPayment;
	}

	@Override
	public Payment viewPaymentDetailsById(Integer paymentId, Integer customerId, String key)
			throws LoginException, CustomerException, OrderException, PaymentException {
		Customer customer = checkLogin(key, customerId);
		
		Optional<Payment> opt = prepo.findById(paymentId);
		
		if(opt.isEmpty()) throw new PaymentException("Invalid Payment Id");
		
		return opt.get();
	}

	@Override
	public List<Payment> getAllPaymentByCustomer(Integer customerId, String key)
			throws LoginException, CustomerException, OrderException, PaymentException {
		Customer customer = checkLogin(key, customerId);
		
		List<Order> olist = customer.getOrderList();
		if(olist.size()==0) throw new OrderException("No Product Purchased By "+customer.getFirstName());
		
		List<Payment> paylist = new ArrayList<>();
		
		for(Order o:olist) {
			if(o.getPayment()!=null) {
				paylist.add(o.getPayment());
			}
		}
		return paylist;
	}

	@Override
	public String cancelPayment(Integer payId, Integer customerId, String key)
			throws LoginException, CustomerException, OrderException, PaymentException {
		
		Customer customer = checkLogin(key, customerId);
		
		Optional<Payment> opt = prepo.findById(payId);
		if(opt.isEmpty()) throw new PaymentException("Invalid Payment Id");
		
		Payment payment = opt.get();
		
		if(payment.getOrder().getCustomer().getCustomerId()!=customer.getCustomerId())
			throw new PaymentException("Invalid Payment Id for Customer");
		
		if(payment.getLocalDate().getDayOfYear()-LocalDate.now().getDayOfYear()>5) {
			throw new PaymentException("Since It's been 5 days You cannot cancel payment directly"
					+ " Please cancel the Order Directly");
		}
		
		payment.getOrder().setOrderStatus("Order Cancelled");
		payment.getOrder().setPayment(null);
		payment.setPaymentStatus(false);
		
		prepo.save(payment);
		
		return "Payment Cancelled Sucessfully";
	}
	
	
	public Customer checkLogin(String key, Integer customerId) throws LoginException, CustomerException {
		Optional<Customer> opt = crepo.findById(customerId);
		if (opt.isEmpty())
			throw new CustomerException("No customer Found with id:- " + customerId);

		Customer customer = opt.get();
		CurrentUserSession cus = cusrepo.findByUuid(key);

		if (cus == null)
			throw new LoginException("Invalid Current Key");
		if (cus.getUserId() != customer.getCustomerId())
			throw new LoginException("Please Login first.....");

		return customer;

	}

	
	
	
	
}
