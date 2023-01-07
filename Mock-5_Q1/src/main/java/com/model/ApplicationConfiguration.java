package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("q1.properties")
@ComponentScan(basePackages = "com.model")
public class ApplicationConfiguration {

	
	@Bean("theList")
	public List<Person> theList(){
		
		List<Person> retObj = new ArrayList<Person>();
		Person p1 = new Person(1, "Afzhal", "a@mail.com", 21, "12345");
		Person p2 = new Person(2, "Ameer", "a2@mail.com", 22, "123456");
		Person p3 = new Person(3, "Akram", "a3@mail.com", 23, "1234567");
		Person p4 = new Person(4, "Ajay", "a4@mail.com", 24, "12345678");
		Person p5 = new Person(5, "Vishal", "v@mail.com", 25, "123456789");
		retObj.add(p1);
		retObj.add(p2);
		retObj.add(p3);
		retObj.add(p4);
		retObj.add(p5);
		
		return retObj;
	}
	
	@Bean("theMap")
	public Map<Person, Gym> theMap(){
		
		Map<Person, Gym> retObj = new HashMap<Person, Gym>();
		Person p1 = new Person(1, "Afzhal", "a1@mail.com", 21, "12345");
		Person p2 = new Person(2, "Ameer", "a2@mail.com", 22, "123456");
		Person p3 = new Person(3, "Akram", "a3@mail.com", 23, "1234567");
		Gym g1= new Gym(1, "SLAM", 3000);
		Gym g2= new Gym(2, "Maverick", 250);
		Gym g3= new Gym(3, "Posh", 4000);
		
		retObj.put(p1, g1);
		retObj.put(p2, g2);
		retObj.put(p3, g3);

		return retObj;
	}
	
	
}
