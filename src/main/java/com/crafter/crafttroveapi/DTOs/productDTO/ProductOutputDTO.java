package com.crafter.crafttroveapi.DTOs.productDTO;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerOutputDTO;
import com.crafter.crafttroveapi.helpers.ProductStatus;
import com.crafter.crafttroveapi.models.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductOutputDTO {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private Boolean isAvailable;
    private String thumbnail;
    private List<String> photos;
    private String pattern;
    private DesignerOutputDTO designer;
    private List<String> categoryList;
    private List<String> keywordList;
    private List<Long> reviewIdList;
}
