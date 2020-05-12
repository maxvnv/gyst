package com.max.gyst.category;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfig.class)
class CategoryControllerIT {

    public static final String TEST_CATEGORY = "test_category";
    public static final String TEST_CATEGORY_2 = "test_category_2";
    public static final String TEST_SUBCATEGORY = "test_subcategory";

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateCategory() throws Exception {
        String categoryResponse = mockMvc.perform(post("/category").content(TEST_CATEGORY))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(TEST_CATEGORY)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Category category = objectMapper.readValue(categoryResponse, Category.class);

        assertThat(category.getId()).isNotNull();
    }

    @Test
    void shouldGetAllCategories() throws Exception {
        mockMvc.perform(post("/category").content(TEST_CATEGORY))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(TEST_CATEGORY)));

        mockMvc.perform(post("/category").content(TEST_CATEGORY_2))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(TEST_CATEGORY_2)));

        mockMvc.perform(get("/category"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(TEST_CATEGORY)))
                .andExpect(content().string(containsString(TEST_CATEGORY_2)));
    }

    @Test
    void shouldCreateSubcategoryForCategory() throws Exception {
        String categoryResponse = mockMvc.perform(post("/category").content(TEST_CATEGORY))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(TEST_CATEGORY)))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Category category = objectMapper.readValue(categoryResponse, Category.class);

        String updatedCategoryAsString = mockMvc
                .perform(post("/category/{categoryId}/subcategory", category.getId()).content(TEST_SUBCATEGORY))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Category updatedCategory = objectMapper.readValue(updatedCategoryAsString, Category.class);

        assertThat(updatedCategory.getSubcategories()).hasSize(1);
        assertThat(updatedCategory.getSubcategories().get(0).getName()).isEqualTo(TEST_SUBCATEGORY);
    }

    @Test
    void shouldFailIfAddingSubcategoryForNonExistingCategory() throws Exception {
        mockMvc.perform(post("/category/{categoryId}/subcategory", 123)
                .content(TEST_SUBCATEGORY))
                .andExpect(status().is4xxClientError());
    }
}