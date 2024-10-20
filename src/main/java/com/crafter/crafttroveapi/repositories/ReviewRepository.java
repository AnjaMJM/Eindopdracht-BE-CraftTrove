package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
