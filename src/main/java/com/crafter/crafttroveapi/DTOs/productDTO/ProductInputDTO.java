package com.crafter.crafttroveapi.DTOs.productDTO;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerInputDTO;
import com.crafter.crafttroveapi.helpers.ProductStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
public class ProductInputDTO {

    @NotEmpty(message = "What name would you like to give your pattern?")
    private String title;

    @NotEmpty(message = "Please add a description, so buyers know what to expect from your pattern")
    private String description;

    private Double price;

    private ProductStatus status;

    private String thumbnail;

    private List<String> photos;

    @NotEmpty(message = "Don't forget to upload a pattern")
    private String pattern;

//    private DesignerInputDTO designer;//many-to-one

    @NotEmpty(message = "Please select at least one category for you pattern")
    private List<String> categoryList;

    private List<String> keywordList;

    private Boolean isAvailable;

}
