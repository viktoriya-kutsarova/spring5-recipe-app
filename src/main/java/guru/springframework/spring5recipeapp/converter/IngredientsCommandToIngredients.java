package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 23-Aug-20
 */
@Component
public class IngredientsCommandToIngredients implements Converter<IngredientCommand, Ingredient> {

	@Synchronized
	@Nullable
	@Override
	public Ingredient convert(IngredientCommand ingredientCommand) {
		if (ingredientCommand == null) {
			return null;
		}
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientCommand.getId());
		ingredient.setAmount(ingredientCommand.getAmount());
		ingredient.setDescription(ingredientCommand.getDescription());

		return ingredient;
	}
}
