package com.eCommerce.Services;

import java.util.List;

import com.eCommerce.exceptions.AddressException;
import com.eCommerce.exceptions.CartException;
import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.OrderException;
import com.eCommerce.model.Order;

public interface OrderService {

	public Order placeOrder(Integer customerId,String key)throws LoginException,CustomerException,OrderException ,CartException,AddressException;
	
	public Order getOrderById(Integer orderId,Integer customerId,String key)throws LoginException,CustomerException,OrderException;
	
	public List<Order> getAllOrder(Integer customerId,String key)throws LoginException,CustomerException,OrderException;
	
	public String cancelOrder(Integer orderId,Integer customerId,String key)throws LoginException,CustomerException,OrderException;
	
	public String deleteOrder(Integer orderId,Integer customerId,String key)throws LoginException,CustomerException,OrderException;
}
