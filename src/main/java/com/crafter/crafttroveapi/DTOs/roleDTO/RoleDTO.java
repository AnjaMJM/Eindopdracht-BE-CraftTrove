package com.crafter.crafttroveapi.DTOs.roleDTO;

import com.crafter.crafttroveapi.helpers.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

    private String name;

    private boolean active;

    public RoleDTO(String name) {
    }

    public RoleDTO() {
    }
}
