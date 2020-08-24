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
		return recipeCommand;
	}
}
