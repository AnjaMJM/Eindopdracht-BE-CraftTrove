package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.userDTO.UserLoginRequestDTO;
import com.crafter.crafttroveapi.exceptions.AuthenticationFailedException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.UserRepository;
import com.crafter.crafttroveapi.security.ApiUserDetails;
import com.crafter.crafttroveapi.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Misschien niet nodig, want zit al in security config
@Service
public class AuthenticationService {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    public void login (UserLoginRequestDTO userLoginRequestDTO) {

        String username = userLoginRequestDTO.getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if(optionalUser.isEmpty()) {
            throw new RecordNotFoundException("Username does not exist");
        }
    }

}
