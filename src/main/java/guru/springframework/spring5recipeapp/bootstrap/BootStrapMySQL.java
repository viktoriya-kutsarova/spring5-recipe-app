package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import guru.springframework.spring5recipeapp.respository.CategoryRepository;
import guru.springframework.spring5recipeapp.respository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by Viktoriya on 06-Dec-20
 */
@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

	private final CategoryRepository categoryRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public BootStrapMySQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

		if (categoryRepository.count() == 0) {
			log.debug("Loading categories");
			loadCategories();
		}

		if (unitOfMeasureRepository.count() == 0) {
			log.debug("Loading unit of measure");
			loadUom();
		}

	}

	private void loadCategories() {
		Category cat1 = new Category();
		cat1.setDescription("American");
		categoryRepository.save(cat1);

		Category cat2 = new Category();
		cat2.setDescription("Italian");
		categoryRepository.save(cat2);

		Category cat3 = new Category();
		cat3.setDescription("Mexican");
		categoryRepository.save(cat3);

		Category cat4 = new Category();
		cat4.setDescription("Fast Food");
		categoryRepository.save(cat4);
	}

	private void loadUom() {
		UnitOfMeasure unitOfMeasure1 = new UnitOfMeasure();
		unitOfMeasure1.setUom("Tablespoon");
		unitOfMeasureRepository.save(unitOfMeasure1);

		UnitOfMeasure unitOfMeasure2 = new UnitOfMeasure();
		unitOfMeasure2.setUom("Teaspoon");
		unitOfMeasureRepository.save(unitOfMeasure2);

		UnitOfMeasure unitOfMeasure3 = new UnitOfMeasure();
		unitOfMeasure3.setUom("Cup");
		unitOfMeasureRepository.save(unitOfMeasure3);
	}
}
