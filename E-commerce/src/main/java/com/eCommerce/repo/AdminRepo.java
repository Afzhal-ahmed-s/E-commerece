package com.eCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{

	public Admin findByUserName(String userName);
}
