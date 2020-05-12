package guru.springframework.spring5recipeapp.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IndexControllerTest {

	IndexController indexController;

	@Mock
	RecipeService recipeService;

	@Mock
	Model model;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		indexController = new IndexController(recipeService);
	}

	@Test
	void getIndexPage() {

		Set<Recipe> recipes = new HashSet<>();
		Recipe recipe = new Recipe();
		recipes.add(recipe);

		when(recipeService.getRecipes()).thenReturn(recipes);

		String viewName = indexController.getIndexPage(model);

		assertEquals("index", viewName);

		verify(model, times(1)).addAttribute("recipes", recipes);

		verify(recipeService, times(1)).getRecipes();
	}
}