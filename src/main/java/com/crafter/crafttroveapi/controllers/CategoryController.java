package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.CategoryOutputDTO;
import com.crafter.crafttroveapi.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryOutputDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryOutputDTO> getCategoryByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(categoryService.getCategoryByName(name));
    }

}
