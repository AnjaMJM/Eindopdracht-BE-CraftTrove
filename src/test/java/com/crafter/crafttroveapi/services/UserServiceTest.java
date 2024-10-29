package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.userDTO.UserInputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.mappers.UserMapper;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;
    @Mock
    UserMapper mapper;

    @Mock
    PasswordEncoder passwordEncoder;


    @BeforeEach
    void setup() {
        when(repository.existsByUsername(anyString())).thenReturn(false);
        when(repository.existsByEmail(anyString())).thenReturn(false);
    }

//    @Test
//    void shouldEncodePasswordWhenCreatingUser() {
//        // Arrange
//        UserInputDTO userInput = new UserInputDTO();
//        userInput.setUsername("testuser");
//        userInput.setPassword("password");
//        userInput.setEmail("test@userland.com");
//        userInput.setDesigner(false);
//
//        User user = new User();
//        user.setPassword(userInput.getUsername());
//        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
//        user.setEmail(userInput.getEmail());
//        user.setDesigner(user.isDesigner());
//        when(mapper.inputToUser(userInput)).thenReturn(user);
//        when(repository.save(any(User.class))).thenReturn(user);
//        // Act
//        UserOutputDTO createdUserDTO = service.createNewUser(userInput);
//        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
//        verify(repository).save(userCaptor.capture());
//        User savedUser = userCaptor.getValue();
//
//        // Assert that the password was encoded
//        assertTrue(passwordEncoder.matches("password", savedUser.getPassword()));
//    }

}