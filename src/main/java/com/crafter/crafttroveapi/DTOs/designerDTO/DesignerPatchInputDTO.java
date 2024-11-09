package com.crafter.crafttroveapi.DTOs.designerDTO;

import com.crafter.crafttroveapi.models.File;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DesignerPatchInputDTO {

    private String brandName;
    private File logo;
    private String brandDescription;
}
