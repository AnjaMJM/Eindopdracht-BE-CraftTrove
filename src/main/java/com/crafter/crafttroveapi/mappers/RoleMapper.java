package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.roleDTO.RoleDTO;
import com.crafter.crafttroveapi.helpers.RoleEnum;
import com.crafter.crafttroveapi.models.Role;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleMapper {

    public RoleDTO roleToOutput(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO dto = new RoleDTO();
        dto.setName(role.getName());
        dto.setActive(role.isActive());
        return dto;
    }

    public Role inputToRole(RoleDTO dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setName(dto.getName());
        role.setActive(dto.isActive());
        return role;
    }


    public Set<RoleDTO> listRoleToOutput(Set<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(this::roleToOutput)
                .collect(Collectors.toSet());
    }

    public Set<Role> listInputToRoles(Set<RoleDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::inputToRole)
                .collect(Collectors.toSet());
    }
}
