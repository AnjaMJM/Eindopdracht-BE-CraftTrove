package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.helpers.RoleEnum;
import com.crafter.crafttroveapi.models.Designer;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.Role;
import com.crafter.crafttroveapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    Optional<User> findByUsernameAndPassword(String username, String password);

}
