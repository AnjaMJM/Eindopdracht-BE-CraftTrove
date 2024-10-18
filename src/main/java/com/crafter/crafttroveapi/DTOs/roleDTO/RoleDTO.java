package com.crafter.crafttroveapi.DTOs.roleDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

    private Long id;

    private String name;

    private boolean active;

    public RoleDTO(Long id) {
    }
}
