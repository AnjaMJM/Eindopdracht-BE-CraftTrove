package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.userDTO.UserInputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.CategoryRepository;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public UserMapper(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public UserOutputDTO userToOutput(User user) {
        UserOutputDTO dto = new UserOutputDTO();
        dto.setId(user.getId());
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

        return dto;
    }

    public User inputToUser(UserInputDTO inputDTO) {
        User user = new User();
        user.setUsername(inputDTO.getUsername());
        user.setPassword(inputDTO.getPassword());
        user.setEmail(inputDTO.getEmail());
        if (inputDTO.getCategoryPreferences() != null) {
            List<Category> categories = categoryRepository.findByNameIgnoreCaseIn(inputDTO.getCategoryPreferences());
            user.setPreferences(categories);
        }
        user.setIsDesigner(inputDTO.getIsDesigner());
        return user;
    }

    // methode werkt nog niet!
//    public User wishlistInputToUser(UserInputDTO inputDTO) {
//        // would this method work to add items to wishlist?
//        User user = new User();
//        if (inputDTO.getProductWishlist() != null) {
//            List<Product> products = productRepository.findByTitle(String.valueOf(inputDTO.getProductWishlist()));
//            user.setWishlist(products);
//        }
//        return user;
//    }
}
