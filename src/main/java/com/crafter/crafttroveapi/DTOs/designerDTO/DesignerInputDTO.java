package com.crafter.crafttroveapi.DTOs.designerDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerInputDTO {

    @NotEmpty(message = "Your brand needs a name too.")
    private String brandName;

    private String logo;

    private String brandDescription;
}
