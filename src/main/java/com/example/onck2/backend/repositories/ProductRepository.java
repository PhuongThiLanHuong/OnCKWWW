package com.example.onck2.backend.repositories;

import com.example.onck2.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByCategory(long id);
}
