package guru.springframework.spring5recipeapp.service;

import java.util.Set;

import guru.springframework.spring5recipeapp.command.IngredientCommand;
import guru.springframework.spring5recipeapp.command.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;

/**
 * Created by Viktoriya on 05-Oct-20
 */
public interface UnitOfMeasureService {
	Set<UnitOfMeasureCommand> listAllUoms();
}
