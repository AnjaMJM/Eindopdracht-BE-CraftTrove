package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByUrl(String url);
}
