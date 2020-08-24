package guru.springframework.spring5recipeapp.controller;

import java.util.HashSet;
import java.util.Set;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
	void testMockMvc() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));

	}

	@Test
	void getIndexPage() {

		//given
		Set<Recipe> recipes = new HashSet<>();
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Recipe recipe1 = new Recipe();
		recipe1.setId(2L);
		recipes.add(recipe);
		recipes.add(recipe1);

		when(recipeService.getAll()).thenReturn(recipes);

		ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

		//when
		String viewName = indexController.getIndexPage(model);

		//then
		assertEquals("index", viewName);
		verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
		verify(recipeService, times(1)).getAll();
		Set<Recipe> setIndexController = argumentCaptor.getValue();
		assertEquals(2, setIndexController.size());

	}
}