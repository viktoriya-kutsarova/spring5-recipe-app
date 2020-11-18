package guru.springframework.spring5recipeapp.controller;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.exceptions.NotFoundException;
import guru.springframework.spring5recipeapp.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerTest {

	RecipeController recipeController;

	@Mock
	RecipeService recipeService;

	MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		recipeController = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders
				.standaloneSetup(recipeController)
				.setControllerAdvice(new ControllerExceptionHandler())
				.build();
	}

	@Test
	void showById() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);



		when(recipeService.findById(anyLong())).thenReturn(recipe);

		mockMvc.perform(get("/recipe/1"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/show"))
				.andExpect(model().attributeExists("recipe"));

	}

	@Test
	void getNewRecipeForm() throws Exception {
		mockMvc.perform(get("/recipe/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	void postNewRecipe() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.save(any())).thenReturn(command);

		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				.param("description", "some string")
				.param("directions", "some directions")
		).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/2"));
	}

	@Test
	void postNewRecipeFail() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.save(any())).thenReturn(command);

		mockMvc.perform(post("/recipe")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.param("id", "")
				).andExpect(status().isOk())
				.andExpect(model().attributeExists("recipe"))
				.andExpect(view().name("recipe/recipeform"));
	}

	@Test
	void getUpdateView() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.findCommandById(any())).thenReturn(command);

		mockMvc.perform(get("/recipe/1/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/recipeform"))
				.andExpect(model().attributeExists("recipe"));
	}

	@Test
	void deleteAction() throws Exception {

		mockMvc.perform(delete("/recipe/1/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/"));

		verify(recipeService, times(1)).deleteById(anyLong());
	}

	@Test
	void getRecipeNotFound() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		when(recipeService.findById(anyLong())).thenThrow(NotFoundException.class);

		mockMvc.perform(get("/recipe/1"))
				.andExpect(status().isNotFound())
				.andExpect(view().name("404error"));
	}

	@Test
	void getRecipeStringId() throws Exception {

		mockMvc.perform(get("/recipe/anystring"))
				.andExpect(status().isBadRequest())
				.andExpect(view().name("400error"));
	}
}