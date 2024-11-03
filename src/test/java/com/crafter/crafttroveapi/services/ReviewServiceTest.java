package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.DTOs.reviewDTO.ReviewInputDTO;
import com.crafter.crafttroveapi.DTOs.reviewDTO.ReviewOutputDTO;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.mappers.ReviewMapper;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.Review;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import com.crafter.crafttroveapi.repositories.ReviewRepository;
import com.crafter.crafttroveapi.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@WebMvcTest -> voor controller testen
class ReviewServiceTest {

    @Mock //mocks a dependency
    ReviewRepository reviewRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    ReviewMapper mapper;

    @InjectMocks // code that needs to be tested
    ReviewService service;

    private Authentication authentication;
    private User user;
    private Product product;
    private ReviewInputDTO input;
    private Review review;
    private ReviewOutputDTO output;

    @BeforeEach
    void setUp() {

        authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getName()).thenReturn("mockUser");

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        user = new User();
        user.setUsername(authentication.getName());

        product = new Product();
        product.setId(1L);
        product.setTitle("funny pattern");

        input = new ReviewInputDTO();

        review = new Review();
        review.setUser(user);
        review.setText("Good");
        review.setProduct(product);
        output = new ReviewOutputDTO();
        output.setReviewText("Good");
        output.setUsername(authentication.getName());
    }


    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void createReview() {
        //arrange
        when(mapper.inputToReview(any())).thenReturn(review);
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(reviewRepository.save(review)).thenReturn(review);
        when(mapper.reviewToOutput(any())).thenReturn(output);
        //act
        ReviewOutputDTO dto = service.createReview(1L, input);
        //assert
        assertEquals(review.getText(), dto.getReviewText());
        assertEquals(user.getUsername(), output.getUsername());
    }

    @Test
    void userNotFoundTest() {
        //arrange

        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        //act

        //assert
        assertThrows(RecordNotFoundException.class, ()-> service.createReview(1L, input));
    }

    @Test
    void productNotFoundTest() {
        //arrange
        when(mapper.inputToReview(any())).thenReturn(review);
        when(productRepository.findById(2L)).thenReturn(Optional.empty());
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));

        // act

        //assert
        assertThrows(RecordNotFoundException.class, ()-> service.createReview(2L, input));
    }

}