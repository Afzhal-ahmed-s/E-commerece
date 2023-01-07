package com.mock5_Q2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mock5_Q2.model.Email;

public interface EmailDao extends JpaRepository<Email, Integer>{

}
