package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductInputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.mappers.ProductMapper;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.repositories.CategoryRepository;
import com.crafter.crafttroveapi.repositories.KeywordRepository;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    ProductService service;

    @Mock
    ProductRepository repository;

    @Mock
    CategoryRepository categoryRepository;

    @Mock
    KeywordRepository keywordRepository;

    @Mock
    ProductMapper mapper;


    private Product item1;
    private Product item2;
    private Product item3;
    private List<Product> productList;
    private Category category;
    private Keyword keyword;
    private ProductOutputDTO outputDto;
    private List<ProductOutputDTO> outputDTOList;
    private ProductInputDTO inputDto;


    @BeforeEach
    void setup() {

        item1 = new Product();
        item1.setId(1L);
        item1.setTitle("fun pattern");
        item1.setDescription("a fun pattern to make");
        item1.setPattern("pattern.pdf");


        item2 = new Product();
        item2.setId(2L);
        item2.setTitle("cute pattern");
        item2.setDescription("a cute pattern to make");
        item2.setPattern("pattern.pdf");


        item3 = new Product();
        item3.setId(3L);
        item3.setTitle("cute pattern");
        item3.setDescription("a cute pattern to make");
        item3.setPattern("pattern.pdf");

        productList = new ArrayList<>();
        productList.add(item1);
        productList.add(item2);
        productList.add(item3);

        category = new Category(1L, "category", "type of craft", productList);

        keyword = new Keyword();
        keyword.setName("keyword");

        inputDto = new ProductInputDTO();
        inputDto.setTitle(item1.getTitle());
        inputDto.setDescription(item1.getDescription());
        inputDto.setPattern(item1.getPattern());
        inputDto.setCategoryList(List.of("category"));

        outputDto = new ProductOutputDTO();
        outputDto.setId(item1.getId());
        outputDto.setTitle(item1.getTitle());
        outputDto.setDescription(item1.getDescription());
        outputDto.setPattern(item1.getPattern());
        outputDto.setCategoryList(List.of("category"));

        outputDTOList = new ArrayList<>();
        outputDTOList.add(outputDto);

    }

    @Test
    void shouldGetAllProducts() {
        //Arrange
        when(repository.findAllAvailableProducts()).thenReturn(productList);
        //Act
        List<ProductOutputDTO> outputList = service.getAllProducts();
        //Assert
        assertEquals(3, outputList.size());
    }

    @Test
    void shouldGetProductById() {
        //Arrange
        when(repository.findById(1L)).thenReturn(Optional.of(item1));
        when(mapper.productToOutput(item1)).thenReturn(outputDto);
        //Act
        ProductOutputDTO output = service.getProductById(1L);
        //Assert
        assertEquals(item1.getId(), output.getId());
        assertEquals(item1.getTitle(), output.getTitle());
        assertEquals(item1.getDescription(), output.getDescription());
        assertEquals(item1.getPattern(), output.getPattern());
    }

    @Test
    void shouldGetProductsByCategory() {
        //Arrange
        when(categoryRepository.findByNameIgnoreCase("category")).thenReturn(Optional.of(category));
        when(mapper.ListProductToOutput(any())).thenReturn(outputDTOList);
        //Act
        List<ProductOutputDTO> output = service.getProductsByCategory("category");
        //Assert
       assertThat(output.size()).isEqualTo(1);
    }

    @Test
    void shouldNotGetProductByCategory() {
        //Arrange
        when(categoryRepository.findByNameIgnoreCase("category")).thenReturn(Optional.empty());
        //Act

        //Assert
        assertThrows(RecordNotFoundException.class, ()-> service.getProductsByCategory("category"));
    }

    @Test
    void shouldGetProductsByKeyword() {
        //Arrange
        when(keywordRepository.findByNameIgnoreCase("keyword")).thenReturn(Optional.of(keyword));
        when(mapper.ListProductToOutput(any())).thenReturn(outputDTOList);
        //Act
        List<ProductOutputDTO> output = service.getProductsByKeywords("keyword");
        //Assert
        assertThat(output.size()).isEqualTo(1);
    }

    @Test
    void shouldNotGetProductsByKeyword() {
        //Arrange
        when(keywordRepository.findByNameIgnoreCase("keyword")).thenReturn(Optional.empty());
        //Act
        //Assert
        assertThrows(RecordNotFoundException.class, ()-> service.getProductsByKeywords("keyword"));
    }

    @Test
    void canCreateProduct() {
        //Arrange
        when(mapper.inputToProduct(inputDto)).thenReturn(item1);
        when(repository.save(item1)).thenReturn(item1);
        when(mapper.productToOutput(item1)).thenReturn(outputDto);
        //Act
        ProductOutputDTO output = service.createNewProduct(inputDto);
        //Assert
        assertEquals(inputDto.getTitle(), output.getTitle());
        assertEquals(inputDto.getDescription(), output.getDescription());
        assertEquals(inputDto.getPattern(), output.getPattern());
        assertEquals(inputDto.getCategoryList(), output.getCategoryList());
    }

    @Test
    void checkTitleDuplicity() {
        //Arrange
        when(repository.existsByTitleIgnoreCase("fun pattern")).thenThrow(DuplicateRecordException.class);
        //Act
        //Assert
        assertThrows(DuplicateRecordException.class, ()-> service.createNewProduct(inputDto));
    }

}