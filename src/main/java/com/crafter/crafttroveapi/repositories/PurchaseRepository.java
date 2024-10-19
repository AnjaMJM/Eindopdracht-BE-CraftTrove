package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
