package com.eCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.model.Seller;

@Repository
public interface sellerRepo extends JpaRepository<Seller, Integer>{

	public Seller findByEmail(String Email);
}
