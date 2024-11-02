package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{name}")
    public ResponseEntity<UserOutputDTO> getUserProfileByUsername(@PathVariable String name){
        return ResponseEntity.ok(userService.getUserByUsername(name));
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<UserOutputDTO> getUserProfileByIdForAdmin(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserByIdForAdmin(id));
    }
}
