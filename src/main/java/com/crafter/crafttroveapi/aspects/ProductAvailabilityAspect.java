package com.crafter.crafttroveapi.aspects;

import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Aspect
@Component
public class ProductAvailabilityAspect {

    @Autowired
    private ProductRepository productRepository;

    @Before("@annotation(@CheckAvailabiltiy) && args(productId)")
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
}