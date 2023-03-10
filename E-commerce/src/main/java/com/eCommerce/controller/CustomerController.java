package com.eCommerce.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.Services.customerService;
import com.eCommerce.exceptions.CustomerException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.model.Address;
import com.eCommerce.model.AddressDTO;
import com.eCommerce.model.Customer;

@RestController
public class CustomerController {

	@Autowired
	private customerService cService;
	
	
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException{
		
		Customer newCustomer = cService.AddCustomer(customer);
		
		return new ResponseEntity<Customer>(newCustomer,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/UpdateCustomer")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestParam String key,@RequestBody  Customer customer) throws CustomerException, LoginException{
		
		Customer updatedcustomer= cService.updateCustomer(key, customer);
		
		return new ResponseEntity<Customer>(updatedcustomer,HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/AddAddress")
	public ResponseEntity<Address> addAddressHandler(@RequestBody AddressDTO address,String key,Integer customerId) throws CustomerException, LoginException{
		
		Address addr= cService.AddAddress(address, key, customerId);
		
		return new ResponseEntity<Address>(addr,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/deleteCustomer")
	public ResponseEntity<String> deleteCustomerHandler(Integer customerId ,String key) throws CustomerException, LoginException{
		
		String message= cService.deleteCustomer(customerId, key);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	
	
}
