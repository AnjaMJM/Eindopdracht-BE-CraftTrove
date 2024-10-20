package com.crafter.crafttroveapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

        @Bean
        public static PasswordEncoder passwordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, JwtRequestFilter jwtRequestFilter) throws Exception {
            http
                    .httpBasic(hp -> hp.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/**").permitAll()
//                            .requestMatchers("/public/**").permitAll()
//                            .requestMatchers("/secure").authenticated()
//                            .requestMatchers("/secure/admin").hasRole("ADMIN")
//                            .requestMatchers("/users/**").hasRole("ADMIN")
//                            .requestMatchers("/secure/user").hasRole("USER")
//                            .anyRequest().denyAll()
                    )
                    .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                    .csrf(csrf -> csrf.disable())
                    .cors(Customizer.withDefaults())
                    .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            ;
            return  http.build();
        }

        @Bean
        public AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder, UserDetailsService apiUserDetailsService) throws Exception {
            var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
            builder.userDetailsService(apiUserDetailsService).passwordEncoder(encoder);
            return builder.build();
        }


}
