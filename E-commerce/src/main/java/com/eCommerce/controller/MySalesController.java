package com.eCommerce.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.Services.MySalesServices;
import com.eCommerce.exceptions.AdminException;
import com.eCommerce.exceptions.LoginException;
import com.eCommerce.exceptions.OrderException;
import com.eCommerce.model.Order;

@RestController
public class MySalesController {

	@Autowired
	private MySalesServices mss;

	@GetMapping("/getSalesForToday")
	public ResponseEntity<List<Order>> SalesForTodayHandler(@RequestParam Integer AdminId,@RequestParam String key)
			throws AdminException, LoginException, OrderException {

		List<Order> list = mss.SalesForToday(AdminId, key);

		return new ResponseEntity<List<Order>>(list, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getSalesForWeek")
	public ResponseEntity<List<Order>> SalesForWeekHandler(@RequestParam Integer AdminId,@RequestParam String key)
			throws AdminException, LoginException, OrderException {
		List<Order> list = mss.SalesForWeek(AdminId, key);

		return new ResponseEntity<List<Order>>(list, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getSalesForMonth")
	public ResponseEntity<List<Order>> SalesForMonthHandler(@RequestParam Integer AdminId,@RequestParam String key)
			throws AdminException, LoginException, OrderException {
		List<Order> list = mss.SalesForMonth(AdminId, key);

		return new ResponseEntity<List<Order>>(list, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getSalesForYear")
	public ResponseEntity<List<Order>> SalesForYearHandler(@RequestParam Integer AdminId,@RequestParam String key)
			throws AdminException, LoginException, OrderException {
		List<Order> list = mss.SalesForYear(AdminId, key);

		return new ResponseEntity<List<Order>>(list, HttpStatus.ACCEPTED);
	}

	@GetMapping("/getSalesBetweenDates")
	public ResponseEntity<List<Order>> SalesBetweenDatesHandler(@RequestParam String date1,@RequestParam String date2,@RequestParam Integer AdminId,@RequestParam String key)
			throws AdminException, LoginException, OrderException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate l1 = LocalDate.parse(date1, dtf);

		LocalDate l2 = LocalDate.parse(date2, dtf);
		
		List<Order> list = mss.SalesBetweenDates(l1, l2, AdminId, key);

		return new ResponseEntity<List<Order>>(list, HttpStatus.ACCEPTED);
	}
}
