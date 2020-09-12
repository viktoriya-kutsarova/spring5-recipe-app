package guru.springframework.spring5recipeapp.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.converter.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converter.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.respository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	void getRecipeById() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		Recipe recipeReturned = recipeService.findById(1L);

		assertNotNull(recipeReturned);
		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, never()).findAll();

	}

	@Test
	void getRecipes() {

		Recipe recipe = new Recipe();
		Set<Recipe> recipesData = new HashSet<>();
		recipesData.add(recipe);

		when(recipeRepository.findAll()).thenReturn(recipesData);

		Set<Recipe> recipes = recipeService.getAll();

		assertEquals(recipes.size(), 1);
		// find all was called only ones
		verify(recipeRepository, times(1)).findAll();
	}

	@Test
	void saveRecipe() {
		//given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);
		recipeCommand.setDescription("descr");
		recipeCommand.setPrepTime(10);

		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipe.setDescription("descr");
		recipe.setPrepTime(10);

		//when
		when(recipeCommandToRecipe.convert(recipeCommand)).thenReturn(recipe);
		when(recipeRepository.save(any())).thenReturn(recipe);
		when(recipeToRecipeCommand.convert(recipe)).thenReturn(recipeCommand);

		//then
		RecipeCommand savedRecipe = recipeService.save(recipeCommand);
		assertNotNull(savedRecipe);
		assertEquals(1l, savedRecipe.getId());
	}

	@Test
	void deleteRecipe() {

		//given
		Long recipeId = 2L;

		//when
		recipeService.deleteById(recipeId);

		// no need for when, as the method has void return type

		//then
		verify(recipeRepository, times(1)).deleteById(recipeId);
	}
}