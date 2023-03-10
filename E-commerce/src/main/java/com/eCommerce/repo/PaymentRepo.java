package com.eCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.model.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Integer>{}
