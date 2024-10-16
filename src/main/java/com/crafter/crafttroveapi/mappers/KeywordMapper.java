package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.keywordDTO.KeywordOutputDTO;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;

import java.util.ArrayList;
import java.util.List;

public class KeywordMapper {

    public static KeywordOutputDTO KeywordToOutput(Keyword keyword) {
        KeywordOutputDTO dto = new KeywordOutputDTO();
        dto.setName(keyword.getName());
        if (keyword.getProducts() != null){
            List<Long> productIdList = new ArrayList<>();
            for(Product product:keyword.getProducts()){
                if(product.getIsAvailable()) {
                    productIdList.add(product.getId());
                }
            }
            dto.setProductIdList(productIdList);
        }
        return dto;
    }
}

