package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Purchase p JOIN p.products prod " +
            "WHERE p.user.id = :userId AND prod.id = :productId AND p.isPayed = true")
    boolean existsByUserIdAndProductIdAndIsPayedTrue(@Param("userId") Long userId, @Param("productId") Long productId);
}

