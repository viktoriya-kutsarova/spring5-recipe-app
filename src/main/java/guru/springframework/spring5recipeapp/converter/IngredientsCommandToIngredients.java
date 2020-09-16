package guru.springframework.spring5recipeapp.converter;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 23-Aug-20
 */
@Component
public class IngredientsCommandToIngredients implements Converter<IngredientCommand, Ingredient> {

	private final UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;

	public IngredientsCommandToIngredients(UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure) {
		this.unitOfMeasureCommandToUnitOfMeasure = unitOfMeasureCommandToUnitOfMeasure;
	}

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

		if (ingredientCommand.getUnitOfMeasure() != null) {
			ingredient.setUnitOfMeasure(unitOfMeasureCommandToUnitOfMeasure.convert(ingredientCommand.getUnitOfMeasure()));
		}

		return ingredient;
	}
}
