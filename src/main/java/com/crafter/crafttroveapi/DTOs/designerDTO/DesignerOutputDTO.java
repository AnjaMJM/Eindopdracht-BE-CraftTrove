package com.crafter.crafttroveapi.DTOs.designerDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DesignerOutputDTO {
    private Long id;

    private String designerName;

    private String brandName;

    private String shopDescription;

//    @OneToOne(mappedBy = "isDesigner")
//    private UserOutputDTO user;

    private List<Long> productIdList;
}