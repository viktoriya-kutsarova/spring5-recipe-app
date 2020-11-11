package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 12-Jun-20
 */
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

	private final IngredientsToIngredientsCommand ingredientsToIngredientsCommand;
	private final CategoryToCategoryCommand categoryToCategoryCommand;
	private final NotesToNotesCommand notesToNotesCommand;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

	public RecipeToRecipeCommand(IngredientsToIngredientsCommand ingredientsToIngredientsCommand,
			CategoryToCategoryCommand categoryToCategoryCommand,
			NotesToNotesCommand notesToNotesCommand,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.ingredientsToIngredientsCommand = ingredientsToIngredientsCommand;
		this.categoryToCategoryCommand = categoryToCategoryCommand;
		this.notesToNotesCommand = notesToNotesCommand;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}

	@Synchronized
	@Nullable
	@Override
	public RecipeCommand convert(Recipe recipe) {
		if (recipe == null) {
			return null;
		}
		RecipeCommand recipeCommand = new RecipeCommand();

		recipeCommand.setId(recipe.getId());
		recipeCommand.setDescription(recipe.getDescription());
		recipeCommand.setPrepTime(recipe.getPrepTime());
		recipeCommand.setCookTime(recipe.getCookTime());
		recipeCommand.setServings(recipe.getServings());
		recipeCommand.setSource(recipe.getSource());
		recipeCommand.setUrl(recipe.getUrl());
		recipeCommand.setDirections(recipe.getDirections());
		recipeCommand.setDifficulty(recipe.getDifficulty());
		recipeCommand.setImage(recipe.getImage());

		if (recipe.getCategories() != null && recipe.getCategories().size() > 0) {
			recipe.getCategories().forEach(
					cat -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(cat)));
		}

		if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
			recipe.getIngredients().forEach(
					ing -> recipeCommand.getIngredients().add(ingredientsToIngredientsCommand.convert(ing)));
		}

		if (recipe.getNotes() != null) {
			recipeCommand.setNotes(notesToNotesCommand.convert(recipe.getNotes()));
		}

		return recipeCommand;
	}
}
