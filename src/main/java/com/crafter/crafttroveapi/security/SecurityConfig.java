package com.crafter.crafttroveapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers(HttpMethod.GET, "/products").permitAll()
                        .requestMatchers(HttpMethod.GET, "/designers").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/keywords").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users").permitAll()

                        .requestMatchers("/users").authenticated()

                        .requestMatchers(HttpMethod.POST, "/purchase").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.POST, "/products/*/review").hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.POST, "/designers").hasAnyAuthority("ROLE_USER")
//                        .requestMatchers("/products").hasAuthority("ROLE_DESIGNER")
                        .anyRequest().permitAll()

//                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasAnyAuthority("ROLE_DESIGNER", "ROLE_ADMIN")
//
//                        .requestMatchers(HttpMethod.DELETE, "/designers").hasAnyAuthority("ROLE_DESIGNER", "ROLE_ADMIN")
//                        .requestMatchers("/designers").hasAnyAuthority("ROLE_DESIGNER")
//
//
//
//                        .anyRequest().denyAll()

                )
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService apiUserDetailsService) throws Exception {
        var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(apiUserDetailsService).passwordEncoder(encoder);
        return builder.build();
    }


}
