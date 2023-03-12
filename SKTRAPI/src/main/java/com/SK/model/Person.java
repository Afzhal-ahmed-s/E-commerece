package com.SK.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sk")
public class Person {

	@Id
	@Column(name="id")
	private int id;
	@Column(name="roll")
	private int rollNo;
	@Column(name="name")
	private String name;
	@Column(name="subject")
	private String subject;
	@Column(name="marks")
	private int marks;
	
}
