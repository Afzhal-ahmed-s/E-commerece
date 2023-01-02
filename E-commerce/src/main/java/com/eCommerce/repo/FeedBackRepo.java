package com.eCommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.model.Customer;
import com.eCommerce.model.Feedback;
import com.eCommerce.model.Order;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback, Integer>{

	public List<Feedback> findByCustomer(Customer customer);
	
	public List<Feedback> findByOrder(Order order);
	
}
