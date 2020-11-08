package guru.springframework.spring5recipeapp.service.impl;

import java.util.Optional;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.converter.IngredientsCommandToIngredients;
import guru.springframework.spring5recipeapp.converter.IngredientsToIngredientsCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.respository.IngredientRepository;
import guru.springframework.spring5recipeapp.respository.RecipeRepository;
import guru.springframework.spring5recipeapp.respository.UnitOfMeasureRepository;
import guru.springframework.spring5recipeapp.service.IngredientService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository, IngredientsToIngredientsCommand ingredientsToIngredientsCommand, IngredientsCommandToIngredients ingredientsCommandToIngredients, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
		this.ingredientsToIngredientsCommand = ingredientsToIngredientsCommand;
		this.ingredientsCommandToIngredients = ingredientsCommandToIngredients;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	@Transactional
	// save or update
	public IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand) {
		if (ingredientCommand == null) {
			return null;
		}
		Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientCommand.getRecipeId());
		//TODO toss error if recipe does not exist
		if (!recipeOptional.isPresent()) {
			log.error("Recipe not found for id: " + ingredientCommand.getId());
			return new IngredientCommand();
		}
		Recipe recipe = recipeOptional.get();
		Optional<Ingredient> ingredientOptional = recipe.getIngredients()
				.stream()
				.filter(ingredient -> ingredient.getId().equals(ingredientCommand.getId()))
				.findFirst();
		if (ingredientOptional.isPresent()) {
			Ingredient ingredientFound = ingredientOptional.get();
			ingredientFound.setDescription(ingredientCommand.getDescription());
			ingredientFound.setAmount(ingredientCommand.getAmount());
			ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
					.findById(ingredientCommand.getUnitOfMeasure().getId())
					.orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //TODO address this
		}
		else {
			Ingredient ingredient = ingredientsCommandToIngredients.convert(ingredientCommand);
			recipe.addIngredient(ingredient);
			ingredient.setRecipe(recipe);
		}

		Recipe savedRecipe = recipeRepository.save(recipe);

		Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients()
				.stream()
				.filter(recipeIngredient -> recipeIngredient.getId().equals(ingredientCommand.getId()))
				.findFirst();

		if (!savedIngredientOptional.isPresent()) {
			savedIngredientOptional = savedRecipe.getIngredients().stream()
					.filter(ri -> ri.getDescription().equals(ingredientCommand.getDescription()))
					.filter(ri -> ri.getAmount().equals(ingredientCommand.getAmount()))
					.filter(ri -> ri.getUnitOfMeasure().getId().equals(ingredientCommand.getUnitOfMeasure().getId()))
					.findFirst();
		}
		//TODO check for fail
		return ingredientsToIngredientsCommand.convert(savedIngredientOptional.get());
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

	@Override
	public void deleteById(Long recipeId, Long idToDelete) {
		log.debug("Deleting ingredient " + recipeId + ":" + idToDelete);

		Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

		if (recipeOptional.isPresent()) {
			Recipe recipe = recipeOptional.get();
			log.debug("found recipe");

			Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
					.filter(i -> i.getId().equals(idToDelete)).findFirst();

			if (ingredientOptional.isPresent()) {
				log.debug("found ingredient");
				Ingredient ingredientToDelete = ingredientOptional.get();
				ingredientToDelete.setRecipe(null);
				recipe.getIngredients().remove(ingredientToDelete);
				recipeRepository.save(recipe);
			}
		}
		else {
			log.debug("Recipe id not found. Id: " + recipeId);
		}
	}
}
