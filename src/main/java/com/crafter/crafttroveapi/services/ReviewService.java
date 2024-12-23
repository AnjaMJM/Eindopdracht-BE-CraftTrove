package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.reviewDTO.ReviewInputDTO;
import com.crafter.crafttroveapi.DTOs.reviewDTO.ReviewOutputDTO;
import com.crafter.crafttroveapi.annotations.CheckAvailability;
import com.crafter.crafttroveapi.exceptions.FailToAuthenticateException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.mappers.ReviewMapper;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.Review;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import com.crafter.crafttroveapi.repositories.PurchaseRepository;
import com.crafter.crafttroveapi.repositories.ReviewRepository;
import com.crafter.crafttroveapi.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, UserRepository userRepository, ProductRepository productRepository, PurchaseRepository purchaseRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.purchaseRepository = purchaseRepository;
        this.reviewMapper = reviewMapper;
    }

    @CheckAvailability
    public ReviewOutputDTO createReview(Long productId, ReviewInputDTO newReview) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Review review = reviewMapper.inputToReview(newReview);

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found with username: " + username);
        }
        User user = optionalUser.get();
        review.setUser(user);
        boolean hasPurchased = purchaseRepository.existsByUserIdAndProductIdAndIsPayedTrue(user.getId(), productId);
        if (!hasPurchased) {
            throw new FailToAuthenticateException("You need to purchase a product, before you can write a review about it");
        }
        Optional<Product> productOptional = productRepository.findByIdAndIsAvailable(productId);
        if (productOptional.isEmpty()) {
            throw new RecordNotFoundException("This product is currently not available");
        }
        review.setProduct(productOptional.get());
        Date dateOfReview = new Date();
        review.setDate(dateOfReview);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.reviewToOutput(savedReview);
    }
}


