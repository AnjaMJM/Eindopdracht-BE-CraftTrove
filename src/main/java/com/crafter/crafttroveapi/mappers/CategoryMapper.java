package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.categoryDTO.CategoryOutputDTO;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryMapper {

    public CategoryOutputDTO categoryToOutput(Category category) {
        CategoryOutputDTO dto = new CategoryOutputDTO();
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        if (category.getProducts() != null){
            List<Long> productIdList = new ArrayList<>();
            for(Product product:category.getProducts()){
                if(product.getIsAvailable()) {
                    productIdList.add(product.getId());
                }
            }
            dto.setProductIdList(productIdList);
        }
        return dto;
    }
}
