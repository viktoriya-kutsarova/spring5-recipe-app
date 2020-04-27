package guru.springframework.spring5recipeapp.respository;

import guru.springframework.spring5recipeapp.domain.Ingredient;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Viktoriya on 24-Apr-20
 */
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
