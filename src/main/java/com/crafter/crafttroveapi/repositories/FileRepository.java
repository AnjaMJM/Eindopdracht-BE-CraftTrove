package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}