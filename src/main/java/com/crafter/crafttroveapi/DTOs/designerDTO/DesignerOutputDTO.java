package com.crafter.crafttroveapi.DTOs.designerDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DesignerOutputDTO {
    private Long id;

    private String username;

    private String brandName;

    private String logo;

    private String brandDescription;

    private List<Long> productIdList;
}
