package com.crafter.crafttroveapi.DTOs.roleDTO;

import com.crafter.crafttroveapi.helpers.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {

    private Long id;

    private RoleEnum name;

    private boolean active;

    public RoleDTO(){

    }
    public RoleDTO(Long id) {
    }
}
