package com.SK.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SK.model.Person;
import com.SK.model.PersonOp;
import com.SK.repo.Repo;

@Service
public class rollToDetailImpl implements rollToDetail{

	@Autowired
	private Repo repo;
	
	@Override
	public PersonOp getPerson(int roll) {
		if(roll <= 0)throw new RuntimeException("provide proper rollNo");
		List<Person>list= repo.findByRollNo(roll);
		System.out.println("check3");

		if(list.size()==0)throw new RuntimeException("No such rollNo exists");
		PersonOp pop= new PersonOp();
		Person p1 = list.get(0);
		
		pop.setId(p1.getId()); pop.setName(p1.getName()); pop.setRollNo(roll);
		
		for(Person e : list) {
			if(e.getSubject().compareToIgnoreCase("chem")==0)pop.setChemMarks(e.getMarks());
			else if(e.getSubject().compareToIgnoreCase("math")==0)pop.setMathMarks(e.getMarks());
			else pop.setPhyMarks(e.getMarks());
		}
		return pop;
	}

	@Override
	public Person savePerson(Person person) throws Exception {

		try {
			List<Person>list = repo.findAll();
			Integer count = list.size();
			if(person!=null) {
				person.setId(++count);
				Person p = repo.save(person);
				return p;
			}
			else throw new RuntimeException("Please provide proper person body");
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}

}
