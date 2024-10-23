package com.crafter.crafttroveapi.DTOs.userDTO;

import com.crafter.crafttroveapi.DTOs.roleDTO.RoleDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserOutputDTO {

    private Long id;
    private String username;
    private String email;
    private List<String> categoryPreferences;
    private List<String> productWishlist;
    private List<Long> purchases;
    private List<String> reviews;
    private boolean isDesigner;
    private Set<RoleDTO> roles;

    private String password;

    // Onderstaande toevoegen aan mappers en uitzoeken waar en hoe ze verder gebruikt worden

    private boolean expired;

    private boolean locked;

    private boolean areCredentialsExpired;

    private boolean enabled;
}
