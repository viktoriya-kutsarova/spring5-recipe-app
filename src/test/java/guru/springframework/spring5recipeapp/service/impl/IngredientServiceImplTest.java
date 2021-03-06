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
import guru.springframework.spring5recipeapp.respository.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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

	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;

	IngredientsCommandToIngredients ingredientsCommandToIngredients;

	IngredientsToIngredientsCommand ingredientsToIngredientsCommand;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		ingredientsCommandToIngredients = new IngredientsCommandToIngredients(new UnitOfMeasureCommandToUnitOfMeasure());
		ingredientsToIngredientsCommand = new IngredientsToIngredientsCommand(new UnitOfMeasureToUnitOfMeasureCommand());
		ingredientService = new IngredientServiceImpl(recipeRepository, ingredientRepository,
				ingredientsToIngredientsCommand, ingredientsCommandToIngredients, unitOfMeasureRepository);
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


	@Test
	public void saveIngredientCommand() {
		//given
		IngredientCommand ingredientCommand = new IngredientCommand();
		ingredientCommand.setId(2L);
		ingredientCommand.setRecipeId(3L);

		Optional<Recipe> recipeOptional = Optional.of(new Recipe());

		Recipe recipeSaved = new Recipe();
		recipeSaved.addIngredient(new Ingredient());
		recipeSaved.getIngredients().iterator().next().setId(2L);

		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
		when(recipeRepository.save(any())).thenReturn(recipeSaved);

		//when
		IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(ingredientCommand);

		//then
		assertEquals(2L, savedIngredient.getId());
		verify(recipeRepository, times(1)).save(any(Recipe.class));
		verify(recipeRepository, times(1)).findById(anyLong());
	}

	@Test
	public void testDeleteIngredientById() {
		Ingredient ingredient = new Ingredient();
		ingredient.setId(3L);
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		recipe.addIngredient(ingredient);
		ingredient.setRecipe(recipe);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		//when
		when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

		ingredientService.deleteById(1L, 3L);

		verify(recipeRepository, times(1)).findById(anyLong());
		verify(recipeRepository, times(1)).save(any());
	}

}
