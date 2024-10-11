package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductInputDTO;
import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.Review;
import com.crafter.crafttroveapi.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    private static CategoryRepository categoryRepository;

    public static ProductOutputDTO ProductToOutput(Product product) {
        ProductOutputDTO dto = new ProductOutputDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setIsAvailable(product.getIsAvailable());
        dto.setThumbnail(product.getThumbnail());
        dto.setPhotos(product.getPhotos());
//        dto.setDesigner(DesignerMapper.DesignerToOutput(product.getDesigner()));
        if (product.getCategories() != null) {
            List<Long> categoryIdList = new ArrayList<>();
            for (Category category : product.getCategories()) {
                categoryIdList.add(category.getId());
            }
            dto.setCategoryIdList(categoryIdList);
        }
        if (product.getKeywords() != null) {
            List<Long> keywordIdList = new ArrayList<>();
            for (Keyword keyword : product.getKeywords()) {
                keywordIdList.add(keyword.getId());
            }
            dto.setKeywordIdList(keywordIdList);
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

    public static Product InputToProduct(ProductInputDTO inputDTO) {
        Product product = new Product();
        product.setTitle(product.getTitle());
        product.setDescription(product.getDescription());
        product.setPrice(product.getPrice());
        product.setIsAvailable(product.getIsAvailable());
        product.setThumbnail(product.getThumbnail());
        product.setPhotos(product.getPhotos());
//        dto.setDesigner(DesignerMapper.DesignerToOutput(product.getDesigner()));
        if (inputDTO.getCategoryIdList() != null) {
            List<Category> categories = categoryRepository.findAllById(inputDTO.getCategoryIdList());
            product.setCategories(categories);
        }
//        if (product.getKeywords() != null){
//            List<Long> keywordIdList = new ArrayList<>();
//            for (Keyword keyword: product.getKeywords()){
//                keywordIdList.add(keyword.getId());
//            }
//            product.setKeywords(keywordIdList);
//      }
        return product;
    }
}
