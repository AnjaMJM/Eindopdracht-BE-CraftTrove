package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.userDTO.UserInputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserLoginRequestDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.CategoryRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserMapper {

    private final CategoryRepository categoryRepository;
    private final RoleMapper roleMapper;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(CategoryRepository categoryRepository, RoleMapper roleMapper, PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.roleMapper = roleMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserOutputDTO userToOutput(User user) {
        UserOutputDTO dto = new UserOutputDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
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
        dto.setDesigner(user.isDesigner());
        dto.setExpired(user.isExpired());
        dto.setLocked(user.isLocked());
        dto.setAreCredentialsExpired(user.isAreCredentialsExpired());
        dto.setEnabled(user.isEnabled());
        dto.setRoles(roleMapper.listRoleToOutput(user.getRoles()));

        return dto;
    }

    public User inputToUser(UserInputDTO inputDTO) {
        User user = new User();
        user.setUsername(inputDTO.getUsername());
        user.setPassword(passwordEncoder.encode(inputDTO.getPassword()));
        user.setEmail(inputDTO.getEmail());
        if (inputDTO.getCategoryPreferences() != null) {
            List<Category> categories = categoryRepository.findByNameIgnoreCaseIn(inputDTO.getCategoryPreferences());
            user.setPreferences(categories);
        }
        user.setDesigner(inputDTO.isDesigner());
        user.setExpired(inputDTO.isExpired());
        user.setLocked(inputDTO.isLocked());
        user.setAreCredentialsExpired(inputDTO.isAreCredentialsExpired());
        user.setEnabled(inputDTO.isEnabled());
        user.setRoles(roleMapper.listInputToRoles(inputDTO.getRoles()));
        return user;
    }

    public User requestInputToUser(UserLoginRequestDTO loginDTO) {
        User result = new User();
        result.setUsername(loginDTO.getUsername());
        result.setPassword(loginDTO.getPassword());
        return result;
    }

//    public UserModel mapToModel(UserChangePassWordRequestDTO userDTO, Long id) {
//        var result = new UserModel(id);
//        result.setPassword(userDTO.getPassword());
//        return result;
//    }

}
