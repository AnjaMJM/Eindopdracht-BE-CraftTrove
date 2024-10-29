package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Designer;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByTitleIgnoreCase(String title);

    List<Product> findByKeywords(List<Keyword> keywords);
    @Query("SELECT p FROM Product p WHERE p.isAvailable = true")
    List<Product> findAllAvailableProducts();

    List<Product> findByTitle(String title);

    List<Product> findByIdIn(List<Long> id);

    Optional<Product> findByIdAndDesigner(Long id, Designer designer);

}
