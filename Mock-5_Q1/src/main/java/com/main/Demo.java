package com.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.model.ApplicationConfiguration;
import com.model.PersonService;

public class Demo {

	public static void main(String[] args) {

		ApplicationContext springContainer = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		
		PersonService ps = springContainer.getBean("personService",PersonService.class);
		
		ps.printAppName();
		ps.printList();
		ps.printMap();
		
		System.out.println("Mock-5 Question 1 done.");
		
	}

}
