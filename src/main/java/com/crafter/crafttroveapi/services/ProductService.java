package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductInputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.annotations.CheckAvailability;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.helpers.CheckType;
import com.crafter.crafttroveapi.mappers.ProductMapper;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.repositories.CategoryRepository;
import com.crafter.crafttroveapi.repositories.KeywordRepository;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;
    private final ProductMapper productMapper;


    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, KeywordRepository keywordRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.keywordRepository = keywordRepository;
        this.productMapper = productMapper;
    }


    public List<ProductOutputDTO> getAllProducts() {
        List<Product> products = productRepository.findAllAvailableProducts();
        List<ProductOutputDTO> dtos = new ArrayList<>();

        for (Product product : products) {
            dtos.add(productMapper.ProductToOutput(product));
        }
        return dtos;
    }

    @CheckAvailability
    public ProductOutputDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException("Product not found"));

        return productMapper.ProductToOutput(product);
    }

    @CheckAvailability(type = CheckType.CATEGORY)
    public List<ProductOutputDTO> getProductsByCategory(String categoryName) {
        Category category = categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new RecordNotFoundException("Category with categoryName " + categoryName + " not found"));

        return productMapper.ListProductToOutput(category.getProducts());
    }

    @CheckAvailability(type = CheckType.KEYWORD)
    public List<ProductOutputDTO> getProductsByKeywords(String keywordName) {
        Keyword keyword = keywordRepository.findByNameIgnoreCase(keywordName)
                .orElseThrow(() -> new RecordNotFoundException("Keyword " + keywordName + " is not found"));

        return productMapper.ListProductToOutput(keyword.getProducts());
    }

    public ProductOutputDTO createNewProduct(ProductInputDTO newProduct) {
        if (productRepository.existsByTitleIgnoreCase(newProduct.getTitle())) {
            throw new DuplicateRecordException("A product with this name already exists");
        }
        Product p = productRepository.save(productMapper.InputToProduct(newProduct));
        return productMapper.ProductToOutput(p);
    }

    @CheckAvailability
    public ProductOutputDTO updateProduct(Long id, ProductInputDTO updatedProduct) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            Product existingProduct = product.get();

// designer stays the same. check if needs to be added to list
            if (updatedProduct.getTitle() != null) {

                if (productRepository.existsByTitleIgnoreCase(updatedProduct.getTitle())) {
                    throw new DuplicateRecordException("A product with this name already exists");
                } else {
                    existingProduct.setTitle(updatedProduct.getTitle());
                }

            }
            if (updatedProduct.getDescription() != null) {
                existingProduct.setDescription(updatedProduct.getDescription());
            }
            if (updatedProduct.getPrice() != null) {
                existingProduct.setPrice(updatedProduct.getPrice());
            }
            if (updatedProduct.getThumbnail() != null) {
                existingProduct.setThumbnail(updatedProduct.getThumbnail());
            }
            if (updatedProduct.getPhotos() != null) {
                existingProduct.setPhotos(updatedProduct.getPhotos());
            }
            if (updatedProduct.getPattern() != null) {
                existingProduct.setPattern(updatedProduct.getPattern());
            }
            if (updatedProduct.getCategoryList() != null) {
                List<Category> categories = categoryRepository.findByNameIgnoreCaseIn(updatedProduct.getCategoryList());
                existingProduct.setCategories(categories);
            }
            if (updatedProduct.getKeywordList() != null) {
                List<Keyword> keywords = new ArrayList<>();
                for (String keywordName : updatedProduct.getKeywordList()) {
                    Keyword keyword = keywordRepository.findByNameIgnoreCase(keywordName)
                            .orElseGet(() -> {
                                Keyword newKeyword = new Keyword();
                                newKeyword.setName(keywordName);
                                return keywordRepository.save(newKeyword);
                            });
                    keywords.add(keyword);
                }
                existingProduct.setKeywords(keywords);
            }
            if (updatedProduct.getIsAvailable() != null) {
                existingProduct.setIsAvailable(updatedProduct.getIsAvailable());
            }
            Product savedProduct = productRepository.save(existingProduct);
            return productMapper.ProductToOutput(savedProduct);
        } else {
            throw new RecordNotFoundException("This product does not exist");
        }
    }


    @Transactional
    @CheckAvailability
    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            product.setIsAvailable(false);

        } else {
            throw new RecordNotFoundException("Product with productId " + productId + " not found");
        }
    }
}
