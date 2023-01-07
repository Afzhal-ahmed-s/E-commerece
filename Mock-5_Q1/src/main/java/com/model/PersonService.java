package com.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;

@Controller
@PropertySource("q1.properties")
public class PersonService {

	
	@Autowired
	@Qualifier("theMap")
	private Map <Person, Gym> theMap;
	// inject 3 entries with valid details 

	@Autowired
	@Qualifier("theList")
	private List<Person> theList;
	// inject List of 5 Person object

	@Value("${q1.appName}")
	private String appName;
	// inject the AppName from the properties file **[ you can define app name as per your convenience. For Example AppName = “GymApplication” ]**

	
	public void printMap(){

	//print all the person's and their gym details from the Map
		for(Map.Entry<Person, Gym> m: theMap.entrySet()) {
			System.out.println(m.getKey()+" -> "+ m.getValue());
		}
		System.out.println();
	}

	public void printList(){

	//sort the List of Person according to the increasing order of the age 

	//print all the sorted Person Details

	for(Person p: theList)System.out.println(p);
	System.out.println();

	}

	public void printAppName(){

	//print the injected appName
	System.out.println("The appName is "+ appName+".");	
	System.out.println();

	}

	public Map<Person, Gym> getTheMap() {
		return theMap;
	}

	public void setTheMap(Map<Person, Gym> theMap) {
		this.theMap = theMap;
	}

	public List<Person> getTheList() {
		return theList;
	}

	public void setTheList(List<Person> theList) {
		this.theList = theList;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	
	
}
