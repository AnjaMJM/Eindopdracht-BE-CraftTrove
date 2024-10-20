package com.crafter.crafttroveapi.DTOs.reviewDTO;

import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReviewOutputDTO {

    private Long id;
    private String username;
    private ProductOutputDTO product;
    private String reviewText;
    private int rating;
    private Date date;

}
