package com.crafter.crafttroveapi.DTOs.designerDTO;

import com.crafter.crafttroveapi.models.File;
import com.crafter.crafttroveapi.models.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerInputDTO {

    private User user;

    @NotEmpty(message = "Your brand needs a name.")
    private String brandName;

    private File logo;

    private String brandDescription;
}
