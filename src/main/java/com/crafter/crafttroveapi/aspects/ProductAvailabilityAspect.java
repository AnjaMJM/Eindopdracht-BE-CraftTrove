package com.crafter.crafttroveapi.aspects;

import com.crafter.crafttroveapi.annotations.CheckAvailability;
import com.crafter.crafttroveapi.exceptions.FailToAuthenticateException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.helpers.CheckType;
import com.crafter.crafttroveapi.models.Category;
import com.crafter.crafttroveapi.models.Keyword;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.repositories.CategoryRepository;
import com.crafter.crafttroveapi.repositories.KeywordRepository;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class ProductAvailabilityAspect {

    private final ProductRepository productRepository;

    private final KeywordRepository keywordRepository;

    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductAvailabilityAspect(ProductRepository productRepository, KeywordRepository keywordRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.keywordRepository = keywordRepository;
        this.categoryRepository = categoryRepository;
    }

    @Before(value = "@annotation(com.crafter.crafttroveapi.annotations.CheckAvailability) && args(productId,..)")
    public void checkProductAvailability(Long productId) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            if (!product.get().getIsAvailable()) {
                throw new FailToAuthenticateException("This product is not available.");
            }
        } else {
            throw new RecordNotFoundException("Product with id " + productId + " not found.");
        }
    }

    @Before("@annotation(checkAvailability) && args(name,..)")
    public void checkAvailability(String name, CheckAvailability checkAvailability) {
        CheckType type = checkAvailability.type();
        if (CheckType.CATEGORY.equals(type)) {
            Category category = categoryRepository.findByNameIgnoreCase(name)
                    .orElseThrow(() -> new RecordNotFoundException("Category " + name + " is not found"));

            boolean hasAvailableProducts = category.getProducts().stream()
                    .anyMatch(Product::getIsAvailable);

            if (!hasAvailableProducts) {
                throw new RecordNotFoundException("No available products for category: " + name);
            }
        } else {
            Keyword keyword = keywordRepository.findByNameIgnoreCase(name)
                    .orElseThrow(() -> new RecordNotFoundException("Keyword " + name + " is not found"));

            boolean hasAvailableProducts = keyword.getProducts().stream()
                    .anyMatch(Product::getIsAvailable);

            if (!hasAvailableProducts) {
                throw new RecordNotFoundException("No available products for keyword: " + name);
            }
        }
    }
}