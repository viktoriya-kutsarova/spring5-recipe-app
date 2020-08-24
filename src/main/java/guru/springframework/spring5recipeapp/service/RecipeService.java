package guru.springframework.spring5recipeapp.service;

import guru.springframework.spring5recipeapp.command.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;

/**
 * Created by Viktoriya on 24-Apr-20
 */
public interface RecipeService extends CrudService<Recipe, RecipeCommand, Long> {
}
