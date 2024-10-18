package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
