package com.crafter.crafttroveapi.integrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ProductControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldCreateProduct() throws Exception {
        String requestJson = """
                {
                    "title": "Lace doyle",
                    "description": "It's like a mini rug for your plants",
                    "price": 9.99,
                    "isAvailable": true,
                     "thumbnail": "thumbnail_image_link_or_data_here",
                     "photos": null,
                     "pattern": "bobbinlace.pdf",
                      "categoryList": ["lace"] ,
                      "keywordList": ["doyle", "vintage"]
                }
                """;

        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }
}
