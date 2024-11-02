package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.userDTO.UserInputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.exceptions.FailToAuthenticateException;
import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.mappers.UserMapper;
import com.crafter.crafttroveapi.models.Role;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.UserRepository;
import com.crafter.crafttroveapi.security.ApiUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public List<UserOutputDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserOutputDTO> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(userMapper.userToOutput(user));
        }
        return dtos;
    }

    public UserOutputDTO getUserByUsername(String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUsername = authentication.getName();
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Optional<User> optionalUser = userRepository.findByUsername(username);
        UserOutputDTO user = new UserOutputDTO();
        if (Objects.equals(username, authUsername) || isAdmin) {
            if (optionalUser.isPresent()) {
                User existingUser = optionalUser.get();
                user = userMapper.userToOutput(existingUser);
            }
        } else {
            throw new FailToAuthenticateException("You are not authorized to see profile of user " + username);
        }
        return user;
    }

    public UserOutputDTO getUserByIdForAdmin(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found");
        }
        User existingUser = optionalUser.get();

        if (!isAdmin) {
            throw new FailToAuthenticateException("You are not authorized to see this user profile");
        }
        return userMapper.userToOutput(existingUser);
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
        userRole.setName("ROLE_USER");
        roles.add(userRole);
        if (newUser.isDesigner()) {
            Role designerRole = new Role();
            designerRole.setName("ROLE_DESIGNER");
            roles.add(designerRole);
        }
        user.setRoles(roles);
        user.setEnabled(true);
        User createdUser = userRepository.save(user);
        return userMapper.userToOutput(createdUser);
    }

    public void deleteUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> optionalWithId = userRepository.findById(id);
        if(optionalWithId.isEmpty()){
            throw new RecordNotFoundException("User not found");
        }
        User withId = optionalWithId.get();

        if(Objects.equals(withId.getUsername(), username)) {
            userRepository.deleteById(id);
        }

    }

    //deactivcateUserByAdmin: set isEnabled op false

    private Optional<User> getOptionalUserModel(Optional<User> user) {
        return user;
    }

    public Optional<User> getUserByName(String username) {
        var user = userRepository.findByUsername(username);
        return getOptionalUserModel(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = getUserByName(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new ApiUserDetails(user.get());
    }
}
