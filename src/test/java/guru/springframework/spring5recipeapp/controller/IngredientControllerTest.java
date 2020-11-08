package guru.springframework.spring5recipeapp.controller;

import java.util.HashSet;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.service.IngredientService;
import guru.springframework.spring5recipeapp.service.RecipeService;
import guru.springframework.spring5recipeapp.service.UnitOfMeasureService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Viktoriya on 13-Sep-20
 */
public class IngredientControllerTest {

	@Mock
	RecipeService recipeService;

	@Mock
	IngredientService ingredientService;

	@Mock
	UnitOfMeasureService unitOfMeasureService;

	IngredientController ingredientController;

	MockMvc mockMvc;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		ingredientController = new IngredientController(recipeService, ingredientService, unitOfMeasureService);
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
	}

	@Test
	public void listIngredients() throws Exception {
		//given
		RecipeCommand recipeCommand = new RecipeCommand();
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);

		//when
		mockMvc.perform(get("/recipe/1/ingredients"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/list"))
				.andExpect(model().attributeExists("recipe"));

		verify(recipeService, times(1)).findCommandById(anyLong());
	}

	@Test
	public void showIngredients() throws Exception {

		IngredientCommand ingredientCommand = new IngredientCommand();
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);

		mockMvc.perform(get("/recipe/1/ingredient/2"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/show"))
				.andExpect(model().attributeExists("ingredient"));

		verify(ingredientService, times(1)).findByRecipeIdAndIngredientId(anyLong(), anyLong());
	}

	@Test
	public void testUpdateIngredientForm() throws Exception {
		IngredientCommand ingredientCommand = new IngredientCommand();
		when(ingredientService.findByRecipeIdAndIngredientId(anyLong(), anyLong())).thenReturn(ingredientCommand);
		when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

		mockMvc.perform(get("/recipe/1/ingredient/2/update"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/ingredientform"))
				.andExpect(model().attributeExists("ingredient"))
				.andExpect(model().attributeExists("uomList"));
	}

	@Test
	public void testSaveOrUpdateIngredient() throws Exception {
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(2L);
		ingredientCommand.setRecipeId(3L);
		when(ingredientService.save(any())).thenReturn(ingredientCommand);

		mockMvc.perform(post("/recipe/1/ingredient")
			.contentType(MediaType.APPLICATION_FORM_URLENCODED)
			.param("id", "2")
			.param("description", "Test description"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/recipe/3/ingredient/2"));
	}

	@Test
	public void testNewIngredientForm() throws Exception {
		//given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(2L);

		//when
		when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommand);
		when(unitOfMeasureService.listAllUoms()).thenReturn(new HashSet<>());

		//then
		mockMvc.perform(get("/recipe/2/ingredients/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("recipe/ingredient/ingredientform"))
				.andExpect(model().attributeExists("ingredient"))
				.andExpect(model().attributeExists("uomList"));

		verify(recipeService, times(1)).findCommandById(anyLong());
	}
}
