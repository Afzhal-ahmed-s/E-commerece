package com.eCommerce.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eCommerce.model.CardDetails;

public interface CardRepo extends JpaRepository<CardDetails, Integer>{}
