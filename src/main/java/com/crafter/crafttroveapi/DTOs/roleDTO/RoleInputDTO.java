package com.crafter.crafttroveapi.DTOs.roleDTO;

import com.crafter.crafttroveapi.helpers.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleInputDTO {

    private String name;
    private boolean active;
}
