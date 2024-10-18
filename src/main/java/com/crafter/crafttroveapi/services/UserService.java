package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.userDTO.UserInputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.mappers.UserMapper;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.RoleRepository;
import com.crafter.crafttroveapi.repositories.UserRepository;
import com.crafter.crafttroveapi.security.ApiUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService (UserRepository userRepository, UserMapper userMapper, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
    }

    public UserOutputDTO createNewUser(UserInputDTO newUser) {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new DuplicateRecordException("This username is already taken");
        }
        if (userRepository.existsByEmail((newUser.getEmail()))) {
            throw new DuplicateRecordException("An account with this e-mailadres already exists");
        }
        User user = userRepository.save(userMapper.inputToUser(newUser));
         return userMapper.userToOutput(user);
    }

//        public boolean createUser(UserInputDTO newUser, List<String> roles) {
//            var validRoles = roleRepository.findByNameIn(roles);
//
//            var user = userMapper.inputToUser(newUser);
//            for (Role role: validRoles ) {
//                user.getRoles().add(role);
//            }
//            updateRolesWithUser(user);
//            var savedUser = userRepository.save(user);
//            newUser.setId(savedUser.getId());
//            return savedUser != null;
//        }

//        private void updateRolesWithUser(User user) {
//            for (Role role: user.getRoles()) {
//                role.getUsers().add(user);
//            }
//        }
//        @Transactional
//        public boolean createUser(UserModel userModel, String[] roles) {
//            return createUser(userModel, Arrays.asList(roles));
//        }
//
        public Optional<User> getUserByUserName(String username) {
            var user = userRepository.findByUsername(username);
            return user;
        }
//
//
//        public Optional<UserModel> getUserByUserNameAndPassword(String username, String password) {
//            var user = userRepository.findByUserNameAndPassword(username, password);
//            return getOptionalUserModel(user);
//        }
//
//        private Optional<UserModel> getOptionalUserModel(Optional<User> user) {
//            if (user.isPresent()) {
//                return Optional.of(userMapper.fromEntity(user.get()));
//            }
//            return Optional.empty();
//        }
//
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> user = getUserByUserName(username);
            if (user.isEmpty()) { throw new UsernameNotFoundException(username);}
            return new ApiUserDetails(user.get());
        }
//
//        public boolean updatePassword(UserModel userModel) {
//            Optional<User> user = userRepository.findById(userModel.getId());
//            if (user.isEmpty()) { throw new UsernameNotFoundException(userModel.getId().toString());}
//            // convert to entity to get the encode password
//            var update_user = userMapper.toEntity(userModel);
//            var entity = user.get();
//            entity.setPassword(update_user.getPassword());
//            return userRepository.save(entity) != null;
//        }
//    }
}
