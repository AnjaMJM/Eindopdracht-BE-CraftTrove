package com.crafter.crafttroveapi.DTOs.productDTO;

import com.crafter.crafttroveapi.DTOs.designerDTO.DesignerInputDTO;
import com.crafter.crafttroveapi.helpers.ProductStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Getter
@Setter
public class ProductInputDTO {

    @NotNull
    @UniqueElements
    private String title;

    @NotNull
    private String description;

    private Double price;

    private ProductStatus status;

    private String thumbnail;

    private List<String> photos;

    @NotNull
    private String pattern;

    private DesignerInputDTO designer;//many-to-one

    @NotNull
    private List<Long> categoryIdList;

    private List<Long> keywordIdList;

}
