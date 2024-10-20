package com.crafter.crafttroveapi.security;

import com.crafter.crafttroveapi.models.Role;
import com.crafter.crafttroveapi.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApiUserDetails implements UserDetails {
    private final User user;
    private  String organisation = "";

    public ApiUserDetails(User user) {
        this.user = user;
    }

    public ApiUserDetails(String username, List<String> roles, String organisation) {
        this.organisation = organisation;
        user = new User();
        user.setUsername(username);

        for (String role : roles) {
            user.getRoles().add(new Role());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role: user.getRoles()) {
            if(role.isActive()) {
                authorities.add(new SimpleGrantedAuthority(String.valueOf(role.getName())));
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return  user.getId() + "::" + user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getIsExpired();
    }
    @Override
    public boolean isAccountNonLocked() {
        return !user.getIsLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.getAreCredentialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnabled();
    }
}
