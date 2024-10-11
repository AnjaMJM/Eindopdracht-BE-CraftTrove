package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
