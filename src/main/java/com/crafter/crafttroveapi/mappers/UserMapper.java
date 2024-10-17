package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public UserOutputDTO userToOutput(User user) {
        UserOutputDTO dto = new UserOutputDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        if (user.getPreferences() != null) {
            List<String> categoryList = new ArrayList<>();
            for (Category category : user.getPreferences()) {
                categoryList.add(category.getName());
            }
            dto.setCategoryPreferences(categoryList);
        }
        if (user.getWishlist() != null) {
            List<String> productList = new ArrayList<>();
            for (Product product : user.getWishlist()) {
                productList.add(product.getTitle());
            }
            dto.setCategoryPreferences(productList);
        }
    }
}
