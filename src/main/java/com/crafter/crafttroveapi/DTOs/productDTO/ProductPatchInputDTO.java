package com.crafter.crafttroveapi.DTOs.productDTO;

import com.crafter.crafttroveapi.models.Designer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductPatchInputDTO {

    private Designer designer;
    private String title;
    private String description;
    private Double price;
    private String thumbnail;
    private List<String> photos;
    private String pattern;
    private List<String> categoryList;
    private List<String> keywordList;
    private Boolean isAvailable;
}
