package com.crafter.crafttroveapi.repositories;

import com.crafter.crafttroveapi.helpers.RoleEnum;
import com.crafter.crafttroveapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);

    List<Role> findByNameIn(List<String> names);
}
