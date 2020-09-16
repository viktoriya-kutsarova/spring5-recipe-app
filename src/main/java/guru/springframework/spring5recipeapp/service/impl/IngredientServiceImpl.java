package guru.springframework.spring5recipeapp.service.impl;

import java.util.Optional;
import java.util.Set;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.converter.IngredientsCommandToIngredients;
import guru.springframework.spring5recipeapp.converter.IngredientsToIngredientsCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.respository.IngredientRepository;
import guru.springframework.spring5recipeapp.respository.RecipeRepository;
import guru.springframework.spring5recipeapp.service.IngredientService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

/**
 * Created by Viktoriya on 16-Sep-20
 */
@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

	private final RecipeRepository recipeRepository;
	private final IngredientRepository ingredientRepository;
	private final IngredientsToIngredientsCommand ingredientsToIngredientsCommand;
	private final IngredientsCommandToIngredients ingredientsCommandToIngredients;

	public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, IngredientsToIngredientsCommand ingredientsToIngredientsCommand, IngredientsCommandToIngredients ingredientsCommandToIngredients) {
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
		this.ingredientsToIngredientsCommand = ingredientsToIngredientsCommand;
		this.ingredientsCommandToIngredients = ingredientsCommandToIngredients;
	}

	@Override
	public Set<Ingredient> getAll() {
		return null;
	}

	@Override
	public Ingredient findById(Long l) {
		return null;
	}

	@Override
	public IngredientCommand findCommandById(Long l) {
		return null;
	}

	@Override
	public IngredientCommand save(IngredientCommand ingredientCommand) {
		return null;
	}

	@Override
	public void deleteById(Long l) {

	}

	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (!recipeOptional.isPresent()) {
			log.error("recipe id not found: " + recipeId);
		}

		Recipe recipe = recipeOptional.get();

		Optional<IngredientCommand> ingredientCommandOptional = recipe.getIngredients().stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientId))
				.map(ingredientsToIngredientsCommand::convert).findFirst();

		if (!ingredientCommandOptional.isPresent()) {
			log.error("ingredient id not found: " + ingredientId);
		}

		return ingredientCommandOptional.get();
	}
}
