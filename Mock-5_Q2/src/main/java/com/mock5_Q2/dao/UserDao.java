package com.mock5_Q2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mock5_Q2.model.User;


@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	public User findByPhoneNo(String phoneNo);
}
