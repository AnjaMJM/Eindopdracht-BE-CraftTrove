package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    Optional<Keyword> findByNameIgnoreCase(String name);
    List<Keyword> findByNameIn(List<String> names);
}
