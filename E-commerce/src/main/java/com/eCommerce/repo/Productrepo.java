package com.eCommerce.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eCommerce.model.Products;

@Repository
public interface Productrepo extends JpaRepository<Products, Integer>{

	public List<Products> findByCategory(String category);
}
