package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.CategoryOutputDTO;
import com.crafter.crafttroveapi.models.Category;

public class CategoryMapper {

    public static CategoryOutputDTO CategoryToOutput(Category category) {
        CategoryOutputDTO dto = new CategoryOutputDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
//        if (category.getProducts() != null){
//            dto.setProducts(ProductMapper.ProductToOutput(category.getProducts()));
//        }
        return dto;
    }
}
