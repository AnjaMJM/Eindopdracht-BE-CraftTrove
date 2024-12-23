package com.crafter.crafttroveapi.controllers;

import com.crafter.crafttroveapi.DTOs.userDTO.UserInputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserLoginRequestDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.exceptions.FailToAuthenticateException;
import com.crafter.crafttroveapi.security.ApiUserDetails;
import com.crafter.crafttroveapi.security.JwtService;
import com.crafter.crafttroveapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager man, JwtService service, UserService userService) {
        this.authManager = man;
        this.jwtService = service;
        this.userService = userService;
    }
    @PostMapping("/signup")
    public ResponseEntity<UserOutputDTO> createNewUser(@RequestBody UserInputDTO newUser) {
        UserOutputDTO createdUser = userService.createNewUser(newUser);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdUser);
    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        UsernamePasswordAuthenticationToken up =
                new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());

        try {
            Authentication auth = authManager.authenticate(up);

            var ud = (ApiUserDetails) auth.getPrincipal();
            String token = jwtService.generateToken(ud);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body("Login successful, Token generated");
        } catch (AuthenticationException ex) {
            throw new FailToAuthenticateException("Username and password do not match");
        }

    }
}
