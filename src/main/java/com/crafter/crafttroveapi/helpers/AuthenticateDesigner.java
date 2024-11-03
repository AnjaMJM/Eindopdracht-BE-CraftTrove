package com.crafter.crafttroveapi.helpers;

import com.crafter.crafttroveapi.exceptions.RecordNotFoundException;
import com.crafter.crafttroveapi.models.Designer;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticateDesigner {

    private final UserRepository userRepository;

    public AuthenticateDesigner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Designer designerAuthentication (){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("User not found");
        }
        User user = optionalUser.get();
        if (!user.isDesigner()) {
            throw new RecordNotFoundException("There is no designer account for user " + username);
        }

        return user.getDesigner();
    }
}
