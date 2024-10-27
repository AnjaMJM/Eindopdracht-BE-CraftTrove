package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.userDTO.UserInputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.helpers.validation.CreateGroup;
import com.crafter.crafttroveapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
