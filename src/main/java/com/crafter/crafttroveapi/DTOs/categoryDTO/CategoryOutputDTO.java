package com.crafter.crafttroveapi.DTOs.categoryDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryOutputDTO {
    private Long id;
    private String name;
    private String description;
    private List<Long> productIdList;
}
