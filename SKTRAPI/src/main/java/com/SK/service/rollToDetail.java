package com.SK.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SK.model.Person;
import com.SK.model.PersonOp;

@Service
public interface rollToDetail {

	public PersonOp getPerson(int roll);
	public Person savePerson(Person person) throws Exception;
	
}
