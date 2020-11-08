package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.command.IngredientCommand;

/**
 * Created by Viktoriya on 16-Sep-20
 */
public interface IngredientService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

	IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);

	void deleteById(Long recipeId, Long idToDelete);
}
