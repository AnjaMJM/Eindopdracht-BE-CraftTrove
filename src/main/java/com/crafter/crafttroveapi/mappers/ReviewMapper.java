package com.crafter.crafttroveapi.mappers;

import com.crafter.crafttroveapi.DTOs.reviewDTO.ReviewInputDTO;
import com.crafter.crafttroveapi.DTOs.reviewDTO.ReviewOutputDTO;
import com.crafter.crafttroveapi.models.Review;
import com.crafter.crafttroveapi.models.User;
import org.springframework.stereotype.Service;

@Service
public class ReviewMapper {

    private final ProductMapper productMapper;

    public ReviewMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public ReviewOutputDTO reviewToOutput(Review review){
        ReviewOutputDTO dto = new ReviewOutputDTO();
        User user = review.getUser();
        dto.setUsername(user.getUsername());
        dto.setId(review.getId());
        dto.setProduct(productMapper.productToOutput(review.getProduct()));
        dto.setReviewText(review.getText());
        dto.setRating(review.getRating());
        dto.setDate(review.getDate());
        return dto;
    }

    public Review inputToReview(ReviewInputDTO inputDTO) {
        Review review = new Review();
        review.setProduct(inputDTO.getProduct());
        review.setText(inputDTO.getReviewText());
        review.setRating(inputDTO.getRating());
        review.setDate(inputDTO.getDate());
        return review;
    }
}
