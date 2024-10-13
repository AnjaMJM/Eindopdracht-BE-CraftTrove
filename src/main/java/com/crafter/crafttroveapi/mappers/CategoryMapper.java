package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.categoryDTO.CategoryOutputDTO;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    public static CategoryOutputDTO CategoryToOutput(Category category) {
        CategoryOutputDTO dto = new CategoryOutputDTO();
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        if (category.getProducts() != null){
            List<Long> productIdList = new ArrayList<>();
            for(Product product:category.getProducts()){
                //add productId to productIdList
                productIdList.add(product.getId());
            }
            dto.setProductIdList(productIdList);
        }
        return dto;
    }
}
