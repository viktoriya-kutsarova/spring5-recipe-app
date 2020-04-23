package guru.springframework.spring5recipeapp.respository;

import java.util.Optional;

import guru.springframework.spring5recipeapp.domain.Category;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Viktoriya on 15-Apr-20
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByDescription(String description);
}
