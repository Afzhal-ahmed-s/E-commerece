package com.SK.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.SK.model.Person;
import com.SK.model.PersonOp;
import com.SK.service.rollToDetail;

@RestController
public class Controller {

	@Autowired
	private rollToDetail service;
	
	@GetMapping(value = "/getValue/{rollNo}")
	public ResponseEntity<PersonOp> getValue(@PathVariable int rollNo){
		PersonOp ans= service.getPerson(rollNo);
		return new ResponseEntity<PersonOp>(ans, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return new ResponseEntity<String>("Hello", HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/savePerson")
	public ResponseEntity<Person> saveStudent(@RequestBody Person person) throws Exception{
		Person p =service.savePerson(person);
		return new ResponseEntity<Person>(p, HttpStatus.CREATED);
	}
}
