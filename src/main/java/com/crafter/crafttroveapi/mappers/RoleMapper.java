package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.roleDTO.RoleDTO;
import com.crafter.crafttroveapi.helpers.RoleEnum;
import com.crafter.crafttroveapi.models.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    public RoleDTO roleToOutput(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO dto = new RoleDTO(role.getId());
        dto.setName(String.valueOf(role.getName()));
        dto.setActive(role.isActive());
        return dto;
    }

    public Role inputToRole(RoleDTO dto) {
        if (dto == null) {
            return null;
        }
        Role role = new Role();
        role.setName(RoleEnum.valueOf(dto.getName()));
        role.setActive(dto.isActive());
        return role;
    }

    public List<RoleDTO> listRoleToOutput(List<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream()
                .map(this::roleToOutput)
                .collect(Collectors.toList());
    }

    public List<Role> listInputToRoles(List<RoleDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
                .map(this::inputToRole)
                .collect(Collectors.toList());
    }
}
