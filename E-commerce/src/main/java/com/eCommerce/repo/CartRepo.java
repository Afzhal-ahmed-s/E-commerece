package com.eCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{}
