package com.max.gyst.category;

import com.max.gyst.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@Import({
        TestCategoryRepositoryHelper.class,
        Application.class,
})
public class TestConfig {

    @Bean
    @Primary
    public CategoryRepository testCategoryRepository(TestCategoryRepositoryHelper testCategoryRepositoryHelper) {
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);
        when(categoryRepositoryMock.save(isA(Category.class))).then(
                (invocationOnMock) -> testCategoryRepositoryHelper.saveCategory(invocationOnMock.getArgument(0))
        );
        when(categoryRepositoryMock.findById(anyLong())).then(
                invocation -> testCategoryRepositoryHelper.getCategoryById(invocation.getArgument(0))
        );
        when(categoryRepositoryMock.findAll()).thenReturn(testCategoryRepositoryHelper.getSavedCategories());
        return categoryRepositoryMock;
    }


}
