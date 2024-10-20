package com.crafter.crafttroveapi.services;

import com.crafter.crafttroveapi.DTOs.roleDTO.RoleDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserInputDTO;
import com.crafter.crafttroveapi.DTOs.userDTO.UserOutputDTO;
import com.crafter.crafttroveapi.exceptions.DuplicateRecordException;
import com.crafter.crafttroveapi.helpers.RoleEnum;
import com.crafter.crafttroveapi.mappers.RoleMapper;
import com.crafter.crafttroveapi.mappers.UserMapper;
import com.crafter.crafttroveapi.models.Role;
import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.RoleRepository;
import com.crafter.crafttroveapi.repositories.UserRepository;
import com.crafter.crafttroveapi.security.ApiUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService (UserRepository userRepository, UserMapper userMapper, RoleMapper roleMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserOutputDTO createNewUser(UserInputDTO newUser) {
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new DuplicateRecordException("This username is already taken");
        }
        if (userRepository.existsByEmail((newUser.getEmail()))) {
            throw new DuplicateRecordException("An account with this e-mailadres already exists");
        }
        User user = new User();

        Set<Role> roles = new HashSet<>();
        RoleDTO userRole = new RoleDTO();
        userRole.setName(RoleEnum.ROLE_USER);
        roles.add(roleMapper.inputToRole(userRole));

        if (newUser.getIsDesigner()) {
            RoleDTO designerRole = new RoleDTO();
            designerRole.setName(RoleEnum.ROLE_DESIGNER);
            roles.add(roleMapper.inputToRole(designerRole));
        }
        user.setRoles(roles);
        User createdUser = userRepository.save(userMapper.inputToUser(newUser));
         return userMapper.userToOutput(createdUser);
    }

//    public Optional<UserOutputDTO> getUserByUserNameAndPassword(String username, String password) {
//        var user = userRepository.findByUsernameAndPassword(username, password);
//        return getOptionalUserModel(user);
//    }

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
