package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.categoryDTO.CategoryOutputDTO;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.mappers.CategoryMapper;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.repositories.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @Mock
    public CategoryMapper categoryMapper;

    @InjectMocks
    private CategoryService service;

    private Product availableProduct;
    private Product unavailableProduct;
    private List<Product> listOfProducts;
    private Category testCategory1;
    private Category testCategory2;
    private List<Category> categories;

    @BeforeEach
    void setup(){
        availableProduct = new Product();
        availableProduct.setIsAvailable(true);
        unavailableProduct = new Product();
        unavailableProduct.setIsAvailable(false);
        listOfProducts = new ArrayList<>();
        listOfProducts.add(availableProduct);
        listOfProducts.add(unavailableProduct);
        testCategory1 = new Category(1L, "hobby", "something to do", listOfProducts);
        testCategory2 = new Category(2L, "pasttime", "something else to do", listOfProducts);
        categories = new ArrayList<>();
        categories.add(testCategory1);
        categories.add(testCategory2);


    }

    @Test
    void getAllCategories() {
        //arrange
        when(repository.findAll()).thenReturn(categories);
        //act
        List<CategoryOutputDTO> categoryDto = service.getAllCategories();
        //assert
        assertEquals(2, categoryDto.size());
    }

    @Test
    void getCategoryByName(){
        //arrange
        CategoryOutputDTO dto = new CategoryOutputDTO();
        dto.setName("hobby");

        when(repository.findByNameIgnoreCase("hobby")).thenReturn(Optional.of(testCategory1));
        when(categoryMapper.categoryToOutput(any())).thenReturn(dto);
        //act
        CategoryOutputDTO categoryDTO = service.getCategoryByName("hobby");
        //assert
        assertNotNull(categoryDTO, "CategoryOutputDTO should not be null");
        assertEquals("hobby", categoryDTO.getName());
    }

    @Test
    void noCategory(){
        //arrange

        when(repository.findByNameIgnoreCase("hobby")).thenReturn(Optional.empty());
        //act

        //assert
        assertThrows(RecordNotFoundException.class, ()-> service.getCategoryByName("hobby"));

    }

}