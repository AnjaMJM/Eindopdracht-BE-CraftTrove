package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.models.Review;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import com.crafter.crafttroveapi.repositories.ReviewRepository;
import com.crafter.crafttroveapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    ReviewRepository reviewRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ReviewService service;

    @Test
    void createReview() {
        //arrange
        Review review = new Review();
        Long id = 1L;
        String username = "testUser";
        ProductOutputDTO product;
        String reviewText = "test review";
        int rating = 5;
        Date date = new Date();
        //act

    }
}