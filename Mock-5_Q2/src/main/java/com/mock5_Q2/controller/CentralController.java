package com.mock5_Q2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mock5_Q2.exceptions.UserException;
import com.mock5_Q2.model.User;
import com.mock5_Q2.serviceAndImplementations.UserService;

@RestController
public class CentralController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "users")
	public ResponseEntity<List<User>> getAllUsers()throws UserException{
		List<User> list = userService.getAllUsers();

		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> saveAdmin(@RequestBody User user) throws UserException {

		User savedUser = userService.createUser(user);

		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}
	
}
