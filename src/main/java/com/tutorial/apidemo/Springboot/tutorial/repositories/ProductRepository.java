package com.tutorial.apidemo.Springboot.tutorial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tutorial.apidemo.Springboot.tutorial.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByProductName(String productName);
}
