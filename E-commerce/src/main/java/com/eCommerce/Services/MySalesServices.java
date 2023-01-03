package com.eCommerce.Services;

import java.time.LocalDate;
import java.util.List;

import com.eCommerce.exceptions.AdminException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.OrderException;
import com.eCommerce.model.Order;

public interface MySalesServices {

	public List<Order> SalesForToday(Integer AdminId,String key) throws AdminException,LoginException,OrderException;
	public List<Order> SalesForWeek(Integer AdminId,String key) throws AdminException,LoginException,OrderException;
	public List<Order> SalesForMonth(Integer AdminId,String key) throws AdminException,LoginException,OrderException;
	public List<Order> SalesForYear(Integer AdminId,String key) throws AdminException,LoginException,OrderException;
	public List<Order> SalesBetweenDates(LocalDate l1,LocalDate l2,Integer AdminId,String key)
			throws AdminException,LoginException,OrderException;
}
