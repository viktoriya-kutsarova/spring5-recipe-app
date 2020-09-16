package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;

/**
 * Created by Viktoriya on 16-Sep-20
 */
public interface IngredientService extends CrudService<Ingredient, IngredientCommand, Long> {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
}
