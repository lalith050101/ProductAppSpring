package com.example.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.application.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
	Optional<Product> findByProductId(Long productId);
}