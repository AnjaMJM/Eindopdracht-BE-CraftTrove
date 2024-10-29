package com.crafter.crafttroveapi.integrationTests;

import com.crafter.crafttroveapi.models.User;
import com.crafter.crafttroveapi.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class AuthenticationControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldPerformUserSignUp() throws Exception {
        String requestJson = """
                {
                    "username": "testuser",
                    "password": "password",
                    "email": "test@userland.com",
                    "designer": false
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/signup")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("username").value("testuser"))
                .andExpect(jsonPath("email").value("test@userland.com"))
                .andExpect(jsonPath("designer").isBoolean());

        User testUser = userRepository.findByUsername("testuser").orElseThrow();
        assertTrue(passwordEncoder.matches("password", testUser.getPassword()));
    }

}
