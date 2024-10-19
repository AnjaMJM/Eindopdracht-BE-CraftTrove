package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.Designer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DesignerRepository extends JpaRepository<Designer, Long> {

    boolean existsByBrandNameIgnoreCase(String name);
    Optional<Designer> findDesignerByBrandNameIgnoreCase(String name);
}
