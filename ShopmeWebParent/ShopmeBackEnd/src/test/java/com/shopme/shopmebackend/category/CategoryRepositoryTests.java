package com.shopme.shopmebackend.category;

import com.shopme.shopme.common.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(value = false)
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testCreateRootCategory() {
        Category category = new Category("Electronics");
        Category savedCategory = categoryRepository.save(category);
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateSubCategory() {
        Category parent = new Category(1);
        Category subCategory = new Category("Desktops", parent);
        Category savedCategory = categoryRepository.save(subCategory);
        assertThat(savedCategory.getId()).isGreaterThan(0);
    }

    @Test
    public void testGetCategory() {
        Category category = categoryRepository.findById(1).get();
        assertThat(category.getChildren().size()).isGreaterThan(0);
    }
}
