package com.crafter.crafttroveapi.aspects;

import com.crafter.crafttroveapi.annotations.CheckAvailability;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.helpers.CheckType;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.repositories.CategoryRepository;
import com.crafter.crafttroveapi.repositories.KeywordRepository;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Aspect
@Component
public class ProductAvailabilityAspect {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Before(value = "@annotation(com.crafter.crafttroveapi.annotations.CheckAvailability) && args(productId,..)")
    public void checkProductAvailability(JoinPoint joinPoint, Long productId) throws Throwable {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            if (!product.get().getIsAvailable()) {
                throw new IllegalStateException("This product is not available.");
            }
        } else {
            throw new RecordNotFoundException("Product with id " + productId + " not found.");
        }
    }
//    @Before("@annotation(com.crafter.crafttroveapi.annotations.CheckAvailability) && args(categoryName,..)")
//    public void checkAvailabilityByCategory(String categoryName) {
//        Category category = categoryRepository.findByNameIgnoreCase(categoryName)
//                .orElseThrow(() -> new RecordNotFoundException("Category " + categoryName + " is not found"));
//
//        // Check if there are available products for this keyword
//        Boolean hasAvailableProducts = category.getProducts().stream()
//                .anyMatch(Product::getIsAvailable);
//
//        if (!hasAvailableProducts) {
//            throw new RecordNotFoundException("No available products for category: " + categoryName);
//        }
//    }
//    @Before("@annotation(com.crafter.crafttroveapi.annotations.CheckAvailability) && args(keywordName,..)")
//    public void checkAvailabilityByKeyword(String keywordName) {
//        Keyword keyword = keywordRepository.findByNameIgnoreCase(keywordName)
//                .orElseThrow(() -> new RecordNotFoundException("Keyword " + keywordName + " is not found"));
//
//        // Check if there are available products for this keyword
//        boolean hasAvailableProducts = keyword.getProducts().stream()
//                .anyMatch(Product::getIsAvailable);
//
//        if (!hasAvailableProducts) {
//            throw new RecordNotFoundException("No available products for keyword: " + keywordName);
//        }
//    }

    @Before("@annotation(checkAvailability) && args(name,..)")
    public List<Product> checkAvailability(String name, CheckAvailability checkAvailability) {
        CheckType type = checkAvailability.type();
        List<Product> availableProducts = new ArrayList<>();
        if (CheckType.CATEGORY.equals(type)) {
            Category category = categoryRepository.findByNameIgnoreCase(name)
                    .orElseThrow(() -> new RecordNotFoundException("Category " + name + " is not found"));

            // Filter out unavailable products
            availableProducts = category.getProducts().stream()
                    .filter(Product::getIsAvailable)  // Only consider available products
                    .collect(Collectors.toList());

            if (availableProducts.isEmpty()) {
                throw new RecordNotFoundException("No available products for category: " + name);
            }


        } else {
            // Handle keyword logic
            Keyword keyword = keywordRepository.findByNameIgnoreCase(name)
                    .orElseThrow(() -> new RecordNotFoundException("Keyword " + name + " is not found"));

            boolean hasAvailableProducts = keyword.getProducts().stream()
                    .anyMatch(Product::getIsAvailable);

            if (!hasAvailableProducts) {
                throw new RecordNotFoundException("No available products for keyword: " + name);
            }
        }
        return availableProducts;
    }

}