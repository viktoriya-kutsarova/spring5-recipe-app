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
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

	private final IngredientsCommandToIngredients ingredientsCommandToIngredients;
	private final CategoryCommandToCategory categoryCommandToCategory;
	private final NotesCommandToNotes notesCommandToNotes;

	public RecipeCommandToRecipe(IngredientsCommandToIngredients ingredientsCommandToIngredients, CategoryCommandToCategory categoryCommandToCategory, NotesCommandToNotes notesCommandToNotes) {
		this.ingredientsCommandToIngredients = ingredientsCommandToIngredients;
		this.categoryCommandToCategory = categoryCommandToCategory;
		this.notesCommandToNotes = notesCommandToNotes;
	}

	@Synchronized
	@Nullable
	@Override
	public Recipe convert(RecipeCommand recipeCommand) {
		if (recipeCommand == null) {
			return null;
		}
		Recipe recipe = new Recipe();
		recipe.setId(recipeCommand.getId());
		recipe.setDescription(recipeCommand.getDescription());
		recipe.setPrepTime(recipeCommand.getPrepTime());
		recipe.setCookTime(recipeCommand.getCookTime());
		recipe.setServings(recipeCommand.getServings());
		recipe.setSource(recipeCommand.getSource());
		recipe.setUrl(recipeCommand.getUrl());
		recipe.setDirections(recipeCommand.getDirections());
		recipe.setDifficulty(recipeCommand.getDifficulty());

		if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
			recipeCommand.getCategories().forEach(
					cat -> recipe.getCategories().add(categoryCommandToCategory.convert(cat)));
		}

		if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
			recipeCommand.getIngredients().forEach(
					ing -> recipe.getIngredients().add(ingredientsCommandToIngredients.convert(ing)));
		}

		recipe.setNotes(notesCommandToNotes.convert(recipeCommand.getNotes()));
		return recipe;
	}
}
