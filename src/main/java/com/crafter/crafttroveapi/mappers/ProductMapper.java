package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductInputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.Review;
import com.crafter.crafttroveapi.repositories.CategoryRepository;
import com.crafter.crafttroveapi.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductMapper {

    private final CategoryRepository categoryRepository;
    private final KeywordRepository keywordRepository;
    private final DesignerMapper designerMapper;

    @Autowired
    public ProductMapper(CategoryRepository categoryRepository, KeywordRepository keywordRepository, DesignerMapper designerMapper) {
        this.categoryRepository = categoryRepository;
        this.keywordRepository = keywordRepository;
        this.designerMapper = designerMapper;
    }

    public ProductOutputDTO productToOutput(Product product) {
        ProductOutputDTO dto = new ProductOutputDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setIsAvailable(product.getIsAvailable());
        dto.setThumbnail(product.getThumbnail());
        dto.setPattern(product.getPatternFile());
        dto.setPhotos(product.getPhotos());
        dto.setDesigner(designerMapper.designerToOutput(product.getDesigner()));
        if (product.getCategories() != null) {
            List<String> categoryList = new ArrayList<>();
            for (Category category : product.getCategories()) {
                categoryList.add(category.getName());
            }
            dto.setCategoryList(categoryList);
        }
        if (product.getKeywords() != null) {
            List<String> keywordList = new ArrayList<>();
            for (Keyword keyword : product.getKeywords()) {
                keywordList.add(keyword.getName());
            }
            dto.setKeywordList(keywordList);
        }
        if (product.getReviews() != null) {
            List<Long> reviewIdList = new ArrayList<>();
            for (Review review : product.getReviews()) {
                reviewIdList.add(review.getId());
            }
            dto.setReviewIdList(reviewIdList);
        }
        return dto;
    }

    public Product inputToProduct(ProductInputDTO inputDTO) {
        Product product = new Product();
        product.setTitle(inputDTO.getTitle());
        product.setDescription(inputDTO.getDescription());
        product.setPrice(inputDTO.getPrice());
        product.setIsAvailable(inputDTO.getIsAvailable());
        product.setThumbnail(inputDTO.getThumbnail());
        product.setPhotos(inputDTO.getPhotos());
        product.setPatternFile(inputDTO.getPattern());
        product.setDesigner(inputDTO.getDesigner());
        if (inputDTO.getCategoryList() != null) {
            List<Category> categories = categoryRepository.findByNameIgnoreCaseIn(inputDTO.getCategoryList());
            product.setCategories(categories);
        }
        if (inputDTO.getKeywordList() != null) {
            List<Keyword> keywords = new ArrayList<>();
            for (String keywordName : inputDTO.getKeywordList()) {
                Keyword keyword = keywordRepository.findByNameIgnoreCase(keywordName)
                        .orElseGet(() -> {
                            Keyword newKeyword = new Keyword();
                            newKeyword.setName(keywordName);
                            return keywordRepository.save(newKeyword);
                        });
                keywords.add(keyword);
            }
            product.setKeywords(keywords);
        }
        return product;
    }

    public List<ProductOutputDTO> ListProductToOutput(List<Product> p) {
        List<Product> products = new ArrayList<>(p);
        List<ProductOutputDTO> outputList = new ArrayList<>();

        for (Product product : products) {
            ProductOutputDTO output = productToOutput(product);
            if (product.getIsAvailable()) {
                outputList.add(output);
            }
        }
        return outputList;
    }
}

