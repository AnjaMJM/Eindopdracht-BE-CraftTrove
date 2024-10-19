package com.crafter.crafttroveapi.DTOs.reviewDTO;

import com.crafter.crafttroveapi.models.Product;
import com.crafter.crafttroveapi.models.User;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewInputDTO {

    private User user;
    private Product product;
    @NotNull
    private String reviewText;
    private int rating;
    private Date date;
}
