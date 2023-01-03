package com.eCommerce.Services;

import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.model.Address;
import com.eCommerce.model.Customer;

public interface customerService {

	public Customer AddCustomer(Customer customer) throws CustomerException;
	
	public Customer updateCustomer(String key,Customer customer)throws CustomerException,LoginException;
	
	public Address AddAddress(Address address,String key,Integer CustomerId)throws CustomerException,LoginException;
	
	public String deleteCustomer(Integer customerId, String key)throws CustomerException,LoginException;
	
}
