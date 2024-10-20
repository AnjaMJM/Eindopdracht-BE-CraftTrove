package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import com.crafter.crafttroveapi.DTOs.reviewDTO.ReviewInputDTO;
import com.crafter.crafttroveapi.DTOs.reviewDTO.ReviewOutputDTO;
import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.repositories.ProductRepository;
import com.crafter.crafttroveapi.services.ProductService;
import com.crafter.crafttroveapi.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final ProductService productService;

    @Autowired
    public ReviewController(ReviewService reviewService, ProductService productService) {
        this.reviewService = reviewService;
        this.productService = productService;
    }

    @PostMapping("/product/{id}/review")
    public ResponseEntity<ReviewOutputDTO> createNewReview(@PathVariable Long id, @RequestBody ReviewInputDTO newReview) {
        ReviewOutputDTO createdReview = reviewService.createReview(newReview, id);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdReview.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdReview);
    }
}
