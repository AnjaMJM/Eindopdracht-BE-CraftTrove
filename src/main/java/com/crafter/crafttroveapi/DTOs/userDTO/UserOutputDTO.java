package com.crafter.crafttroveapi.DTOs.userDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserOutputDTO {

    private String username;
    private String email;
    private List<String> categoryPreferences;
    private List<String> productWishlist;
    private List<Long> purchases;
    private List<String> reviews;
    private Boolean isDesigner;
}
