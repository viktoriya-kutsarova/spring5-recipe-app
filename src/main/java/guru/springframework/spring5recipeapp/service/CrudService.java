package guru.springframework.spring5recipeapp.service;

import java.util.Set;

import guru.springframework.spring5recipeapp.domain.Recipe;

/**
 * Created by Viktoriya on 24-Apr-20
 */
public interface CrudService<T, TCommand, ID> {

	Set<T> getAll();

	T findById(ID l);

	TCommand save(TCommand tCommand);
}
