package com.crafter.crafttroveapi.DTOs.productDTO;

import com.crafter.crafttroveapi.models.Designer;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInputDTO {

    private Designer designer;

    @NotEmpty(message = "What name would you like to give your pattern?")
    private String title;

    @NotEmpty(message = "Please add a description, so costumers know what to expect from your pattern")
    private String description;

    private Double price;

    private String thumbnail;

    private List<String> photos;

    @NotEmpty(message = "Don't forget to upload a pattern")
    private String pattern;

    @NotEmpty(message = "Please select at least one category for you pattern")
    private List<String> categoryList;

    private List<String> keywordList;

    private Boolean isAvailable;

}
