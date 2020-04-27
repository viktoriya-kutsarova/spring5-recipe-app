package guru.springframework.spring5recipeapp.service;

import java.util.Set;

/**
 * Created by Viktoriya on 24-Apr-20
 */
public interface CrudService<T, ID> {

	Set<T> getRecipes();
}
