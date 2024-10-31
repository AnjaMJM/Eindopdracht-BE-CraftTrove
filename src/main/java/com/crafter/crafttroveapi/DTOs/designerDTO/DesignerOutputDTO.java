package com.crafter.crafttroveapi.DTOs.designerDTO;

import com.crafter.crafttroveapi.DTOs.fileDTO.FileLogoOutputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.models.File;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DesignerOutputDTO {

    private String username;

    private String brandName;

    private String logoUrl;

    private String brandDescription;

    private List<Long> productIdList;
}
