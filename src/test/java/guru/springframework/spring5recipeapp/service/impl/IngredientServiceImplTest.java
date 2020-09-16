package guru.springframework.spring5recipeapp.service.impl;

import java.util.Optional;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.converter.IngredientsCommandToIngredients;
import guru.springframework.spring5recipeapp.converter.IngredientsToIngredientsCommand;
import guru.springframework.spring5recipeapp.converter.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.spring5recipeapp.converter.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.respository.IngredientRepository;
import guru.springframework.spring5recipeapp.respository.RecipeRepository;
import guru.springframework.spring5recipeapp.service.IngredientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Viktoriya on 16-Sep-20
 */
public class IngredientServiceImplTest {

	IngredientServiceImpl ingredientService;

	@Mock
	IngredientRepository ingredientRepository;

	@Mock
	RecipeRepository recipeRepository;

	IngredientsCommandToIngredients ingredientsCommandToIngredients;

	IngredientsToIngredientsCommand ingredientsToIngredientsCommand;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		ingredientsCommandToIngredients = new IngredientsCommandToIngredients(new UnitOfMeasureCommandToUnitOfMeasure());
		ingredientsToIngredientsCommand = new IngredientsToIngredientsCommand(new UnitOfMeasureToUnitOfMeasureCommand());
		ingredientService = new IngredientServiceImpl(recipeRepository, ingredientRepository,
				ingredientsToIngredientsCommand, ingredientsCommandToIngredients);
	}

	@Test
	public void findByRecipeIdAndIngredientId() throws Exception {
		// given
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1L);

		Ingredient ingredient2 = new Ingredient();
		ingredient2.setId(2L);

		Ingredient ingredient3 = new Ingredient();
		ingredient3.setId(3L);

		recipe.addIngredient(ingredient1);
		recipe.addIngredient(ingredient2);
		recipe.addIngredient(ingredient3);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		//then
		IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 3L);

		//when
		assertEquals(Long.valueOf(3L), ingredientCommand.getId());
		assertEquals(Long.valueOf(1L), ingredientCommand.getRecipeId());
		verify(recipeRepository, times(1)).findById(anyLong());
	}


}
