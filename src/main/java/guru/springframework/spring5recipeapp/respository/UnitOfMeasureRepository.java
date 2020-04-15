package guru.springframework.spring5recipeapp.respository;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;

import org.springframework.data.repository.CrudRepository;

/**
 * Created by Viktoriya on 15-Apr-20
 */
public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
