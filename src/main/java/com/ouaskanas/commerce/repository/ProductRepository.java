package com.ouaskanas.commerce.repository;

import com.ouaskanas.commerce.model.Category;
import com.ouaskanas.commerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findProductsByProductName(String productName);

    List<Product> findProductsByCategory(Category category);
}
