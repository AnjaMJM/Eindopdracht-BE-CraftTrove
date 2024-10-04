package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.CategoryOutputDTO;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.crafter.crafttroveapi.mappers.CategoryMapper.CategoryToOutput;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryOutputDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryOutputDTO> dtos = new ArrayList<>();

        for(Category category:categories) {
            dtos.add(CategoryToOutput(category));
        }
        return dtos;
    }

    public CategoryOutputDTO getCategoryByName(String name){
        Category category = categoryRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new IndexOutOfBoundsException("Category not found with name " + name));

       return CategoryToOutput(category);
    }

    public CategoryOutputDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IndexOutOfBoundsException("Category not found"));
        return CategoryToOutput(category);
    }

}
