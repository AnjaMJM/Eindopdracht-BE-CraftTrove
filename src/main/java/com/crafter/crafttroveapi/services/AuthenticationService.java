//package com.crafter.crafttroveapi.services;
//
//import com.crafter.crafttroveapi.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//
//import java.util.Optional;

@Service
public class AuthenticationService {


//    private UserDetailsService userDetailsService;
//
//
//    private PasswordEncoder passwordEncoder;
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public AuthenticationService(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
//        this.userDetailsService = userDetailsService;
//        this.passwordEncoder = passwordEncoder;
//        this.userRepository = userRepository;
//    }
//
//
//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder);
//        return authProvider;
//    }

//    public void login (UserLoginRequestDTO userLoginRequestDTO) {
//
//        String username = userLoginRequestDTO.getUsername();
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//
//        if(optionalUser.isEmpty()) {
//            throw new RecordNotFoundException("Username does not exist");
//        }
//    }

}
