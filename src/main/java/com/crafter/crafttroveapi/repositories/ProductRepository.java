package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByTitleIgnoreCase(String title);

    List<Product> findByKeywords(Keyword keyword);
    @Query("SELECT p FROM Product p WHERE p.isAvailable = true")
    List<Product> findAllAvailableProducts();

}
