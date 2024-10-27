package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.userDTO.UserInputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.helpers.RoleEnum;
import com.crafter.crafttroveapi.mappers.UserMapper;
import com.crafter.crafttroveapi.models.Role;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.UserRepository;
import com.crafter.crafttroveapi.security.ApiUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;

    }

    public UserOutputDTO createNewUser(UserInputDTO newUser) {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new DuplicateRecordException("This username is already taken");
        }
        if (userRepository.existsByEmail((newUser.getEmail()))) {
            throw new DuplicateRecordException("An account with this e-mailadres already exists");
        }
        User user = userMapper.inputToUser(newUser);

        Set<Role> roles = new HashSet<>();
        Role userRole = new Role();
        userRole.setName(RoleEnum.ROLE_USER);
        roles.add(userRole);
        if (newUser.isDesigner()) {
            Role designerRole = new Role();
            designerRole.setName(RoleEnum.ROLE_DESIGNER);
            roles.add(designerRole);
        }
        user.setRoles(roles);
        user.setEnabled(true);
        User createdUser = userRepository.save(user);
        return userMapper.userToOutput(createdUser);
    }

    public List<UserOutputDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserOutputDTO> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(userMapper.userToOutput(user));
        }
        return dtos;
    }

    public Optional<User> getUserByUserName(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User does not exist");
        }
        return optionalUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = getUserByUserName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new ApiUserDetails(user.get());
    }

    public User updatePassword(UserInputDTO inputDTO) {
        Optional<User> user = userRepository.findByUsername(inputDTO.getUsername());
        if (user.isEmpty()) {
            throw new RecordNotFoundException("User not found");
        }
        // convert to entity to get the encode password
        var update_user = userMapper.inputToUser(inputDTO);
        var entity = user.get();
        entity.setPassword(update_user.getPassword());
        return userRepository.save(entity);
    }

}
