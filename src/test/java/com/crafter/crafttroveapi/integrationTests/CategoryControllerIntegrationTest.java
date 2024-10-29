package com.crafter.crafttroveapi.integrationTests;


import com.crafter.crafttroveapi.DTOs.productDTO.ProductOutputDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class CategoryControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldGetCategories() throws Exception {

        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/categories")
                        .contentType(APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Sewing"))
                .andExpect(jsonPath("$[0].description").value("The craft of fastening or attaching objects using stitches made with a needle and thread. Sewing is used in the making of garments, accessories, and home decor items."))
                .andExpect(jsonPath("$[0].productIdList").isArray())
                .andExpect(jsonPath("$[0].productIdList[0]").value(1002));

    }
}
