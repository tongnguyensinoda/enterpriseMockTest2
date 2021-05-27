package com.example.demo.repository;

import com.example.demo.entity.Country;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p where p.productName = ?1")
    Optional<Product> findProductByProductName(String productName);
    @Query("SELECT p FROM Product p WHERE lower(CONCAT(p.productName, ' ', p.brand, ' ', p.color, ' ', p.price, ' ',p.model)) LIKE %?1%")
    List<Product> search(String keyword);
    @Query("SELECT p FROM Product p where p.country =?1")
    Product findProductByCountry(Country country);
    @Query("SELECT p FROM Product p where p.productName = ?1")
    Page<Product> findByNameLike(String productName, Pageable pageable);
}
