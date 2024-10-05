package com.crafter.crafttroveapi.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryOutputDTO {
    private Long id;
    private String name;
    private String description;
//    private List<ProductOutputDTO> products;
}
