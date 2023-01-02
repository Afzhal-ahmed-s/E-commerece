package com.eCommerce.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.model.Address;
import com.eCommerce.model.Customer;
import com.eCommerce.model.Order;
import com.eCommerce.model.Payment;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{

	public List<Order> findByCustomer(Customer customer);

	public List<Order> findByOrderDate(LocalDate orderDate);

	public List<Order> findByOrderDateBetween(LocalDate s_orderDate, LocalDate e_orderDate);

	public List<Order> findByOrderDateGreaterThanEqual(LocalDate orderDate);
}
