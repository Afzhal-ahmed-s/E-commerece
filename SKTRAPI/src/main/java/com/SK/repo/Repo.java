package com.SK.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SK.model.Person;

@Repository
public interface Repo extends JpaRepository<Person, Integer> {

	public List<Person> findByRollNo(Integer roll);
}
