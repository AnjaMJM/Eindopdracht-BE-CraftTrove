package com.crafter.crafttroveapi.DTOs.userDTO;

import com.crafter.crafttroveapi.DTOs.validation.CreateGroup;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInputDTO {

    @NotEmpty(groups = CreateGroup.class, message = "Please enter a username, with at least 4 characters and maximum 20 charachters")
    @Min(value = 4, message = "Your username needs at least 4 characters")
    @Max(value = 20, message = "Your username can have maximum 20 characters")
    private String username;

    @NotEmpty(groups = CreateGroup.class, message = "Please enter a password with at least 8 characters")
    @Min(value = 8, message = "Your password needs at least 8 characters")
    private String password;

    @NotEmpty(groups = CreateGroup.class, message = "Please enter your e-mailadres")
    @Email(message = "E-mailadres should be valid")
    private String email;

    private List<String> categoryPreferences;

    private List<String> productWishlist;

    private Boolean isDesigner;
}
