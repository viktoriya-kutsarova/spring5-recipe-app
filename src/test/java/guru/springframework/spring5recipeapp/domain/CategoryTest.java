package guru.springframework.spring5recipeapp.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CategoryTest {

	Category category;

	@BeforeEach
	void setUp() {
		category = new Category();
	}

	@Test
	void getId() {
		Long idValue = 4l;

		category.setId(idValue);

		assertEquals(idValue, category.getId());
	}

	@Test
	void getDescription() {
	}

	@Test
	void getRecipes() {
	}
}